
$(document).ready(function(){
	//현재 스위치 상태 -> 시작은 off임(단일패키지), on == 3종패키지
	let switchMode = "off";
	
	//switch에 대한 js코드
	$(".switch-outer").on("click",function(event){
		const switchOuter = $(this);
		const switchInner = switchOuter.find(".switch-inner");
		
		if(switchMode=="off"){
			//스위치 스타일 변경해주는 코드
			switchOuter.addClass("on-outer");
			switchInner.addClass("on-inner");
			
			//출력할 패키지 선택해주는 코드
			$(".single-package").addClass("deactivate");
			$(".triple-package").removeClass("deactivate");
			
			switchMode = "on";
		}else{
			//스위치 스타일 변경해주는 코드
			switchOuter.removeClass("on-outer");
			switchInner.removeClass("on-inner");
			
			//출력할 패키지 선택해주는 코드
			$(".triple-package").addClass("deactivate");
			$(".single-package").removeClass("deactivate");
			
			switchMode = "off";
		}
	});
	
	//////////////////////////////////////////////////////
	
	//사용자가 입력한 글자수 카운팅
    $("textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".counter");
        
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
    });
    
 ////////////////////////////////////////////////////////// 
//    입력 금액 한도 범위 벗어나면 주의 문구 붙여줌
    $(".price").each(function(){
    	$(this).find("input").on('input',function(){
    		//parseFormattedNumber() : 문자열값을 정수로 파싱해주는 함수
    		const value = parseFormattedNumber($(this).val());
    		$(this).parent().css("position","relative");
            let alertMsg = $("<div>5,000~ 999,999,000 원 까지 만 입력 가능합니다</div>")
            					.attr("class", "alert-msg");
            // 부모의 마진바텀 값
            let parentMarginBottom = $(this).parent().css("margin-bottom");
            // 부모가 마진바텀이 있으면 true저장 , 아니면 false
            let hasMargin = (parentMarginBottom!=="auto" && parentMarginBottom!=="" && parentMarginBottom!=="0px");
            
            //5000미만, 999,999,000 초과면 alert-msg 띄움
            if (value < 5000 || value > 999999000) {  
            	if(hasMargin){ // 부모가 margin-bottom 있는 경우 => alert-msg가 부모의 마진만큼 위로 올라가야함
            		let cssProp = {
            				"position":"relative",
                    		"top":"-"+parentMarginBottom};
            		alertMsg.css(cssProp);
            		$(this).parent().next(".alert-msg").remove();//alertMsg 가 누적으로 쌓이는걸 방지
                    $(this).parent().addClass("alert-style");
                    $(this).parent().after(alertMsg);
            	}else{ // 부모가 margin-bottom 없는 경우 
            		$(this).parent().next(".alert-msg").remove();//alertMsg 가 누적으로 쌓이는걸 방지
                    $(this).parent().addClass("alert-style");
                    $(this).parent().after(alertMsg);
            	}
            } 
            //5000~999,999,000 안에 해당된 경우
            else{
            	$(this).parent().removeClass("alert-style");
                $(this).parent().next(".alert-msg").remove();
            }
            
            //이거 없으면 오류남
            if(value===""){
            	$(this).parent().removeClass("alert-style");
                $(this).parent().next(".alert-msg").remove();
            }
        
    	});
    });
    // (문자열)"20,122,000"  => (정수)20122000 파싱
    function parseFormattedNumber(value) {
    	  // 쉼표 제거
    	  var cleanedValue = value.replace(/,/g, '');
    	  // 정수로 파싱
    	  var parsedValue = parseInt(cleanedValue, 10);
    	  return parsedValue;
    	}
    
/////////////////////////////////////////////////////////////////
    //가격 입력 하면 1000원 미만은 0으로 내림해주는 코드 + 1000마다 "," 추가해주기
    $('.price').each(function(){
    	let priceInput = $(this).find("input");
    	priceInput.on('blur',function(){
    		if($(this).val()!=0){
			var value = parseInt($(this).val());
			  // 1000원 밑으로는 0으로 내림
			  var roundedValue = Math.floor(value / 1000) * 1000;
			  // 1000마다 쉼표추가
			  var formattedValue = addCommas(roundedValue);
			  $(this).val(formattedValue);
    		}
    	});
    });
	// 쉼표 추가해주는 정규식
	function addCommas(value) {
		  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
    ////////////////////////////////////////////
	//작업일 범위 벗어나게 입력하면 주의 문구 
	$(".date").each(function(){
    	$(this).find("input").on('input',function(){
    		//parseFormattedNumber() : 문자열값을 정수로 파싱해주는 함수
    		const value = $(this).val();
    		$(this).parent().css("position","relative");
            let alertMsg = $("<div>0 ~ 90 일 까지 만 입력 가능합니다</div>")
            .attr("class", "alert-msg");
            // 부모의 마진바텀 값
            let parentMarginBottom = $(this).parent().css("margin-bottom");
            // 부모가 마진바텀이 있으면 true저장 , 아니면 false
            let hasMargin = (parentMarginBottom!=="auto" && parentMarginBottom!=="" && parentMarginBottom!=="0px");
            
            //0일 미만, 90일 초과면 alert-msg 띄움
            if (value < 0 || value > 90) {  
            	if(hasMargin){ // 부모가 margin-bottom 있는 경우 => alert-msg가 부모의 마진만큼 위로 올라가야함
            		let cssProp = {
            				"position":"relative",
                    		"top":"-"+parentMarginBottom};
            		alertMsg.css(cssProp);
            		$(this).parent().next(".alert-msg").remove();//alertMsg 가 누적으로 쌓이는걸 방지
                    $(this).parent().addClass("alert-style");
                    $(this).parent().after(alertMsg);
            	}else{ // 부모가 margin-bottom 없는 경우 
            		$(this).parent().next(".alert-msg").remove();//alertMsg 가 누적으로 쌓이는걸 방지
                    $(this).parent().addClass("alert-style");
                    $(this).parent().after(alertMsg);
            	}
            } 
            //0~90 안에 해당된 경우
            else{
            	$(this).parent().removeClass("alert-style");
                $(this).parent().next(".alert-msg").remove();
            }
            
            //이거 없으면 오류남
            if(value===""){
            	$(this).parent().removeClass("alert-style");
                $(this).parent().next(".alert-msg").remove();
            }
    	});
    });
   /////////////////////////////////////////////// 
   
    //드롭다운 : list 내리기, value 띄우기 구현해야함
    $(".dropdown").on("click",function(){
    	//지금 내가 접근한 드롭다운을 저장
    	let thisDropdown = $(this);
    	let list = $(this).siblings("ul");
    	if(list.hasClass("deactivate")){
    		list.removeClass("deactivate");
    	}else{
    		list.addClass("deactivate");
    	}
    	
    	//드롭다운 옵션 클릭하면 해당하는 value를 출력
    	list.find("li").each(function(){
    		$(this).on("click",function(){
        		let value = $(this).find(".revision-value").text();
        		thisDropdown.find(".revision-placeholder").text(value).css("color","black");
        		if(thisDropdown.hasClass("single")){
        			$("#revision").val(value);//input태그에 value저장
        		}else if(thisDropdown.hasClass("triple-basic")){
        			$("#basic_revision").val(value);//input태그에 value저장
        		}else if(thisDropdown.hasClass("triple-economy")){
        			$("#economy_revision").val(value);//input태그에 value저장
        		}else if(thisDropdown.hasClass("triple-premium")){
        			$("#premium_revision").val(value);//input태그에 value저장
        		}
        		
        		list.addClass("deactivate");
    		});
    		
    	});
    });
    
   
    //추가 옵션 설정 - 체크박스 선택하면 체크된 옵션에 대해 정보 입력칸 나옴
    let optionMap = {};
    $(".option-box-row01 input").on("change",function(){
    	if($(this).is(":checked")){
    		$(this).parent().siblings(".option-box-row02").removeClass("deactivate");
    		
    		
    	}else{
    		$(this).parent().siblings(".option-box-row02").addClass("deactivate");
    		
    	}
    });
    ///////////////////////////////////////////////
    // 필수 사항 입력 안 한 채로 "다음" 버튼 누른경우
    $(".form-buttons-next").on("click",function(){
    	let isValid = true;
    	const alertMsg = $("<div>항목을 채워주세요</div>").attr("class", "alert-msg");
    	
    	//단일 패키지 모드인경우
    	if(switchMode==="off"){
    		$('.single-package textarea[required]').each(function() {
	  			  if ($(this).val().trim() === '') {
	  				  $(this).parent().parent().addClass('alert-style');
	  				  isValid=false;
	  			  }
    		});
    		$('.single-package input[required]').each(function() {
	  			  if ($(this).val().trim() === '') {
	  				  $(this).parent().addClass('alert-style');
	  				  isValid=false;
	  			  }
    		});
    		let value = $(".single-package-revision").find(".revision-placeholder").text(); 
    		if(isNaN(value)){
    			$(".single-package-revision").addClass('alert-style');
    			isValid=false;
    		}
    		
    	//3단 패키지 모드인경우
    	}else{
    		$('.triple-package textarea[required]').each(function() {
	  			  if ($(this).val().trim() === '') {
	  				  $(this).parent().parent().addClass('alert-style');
	  				  isValid=false;
	  			  }
    		});
	  		$('.triple-package input[required]').each(function() {
	  			  if ($(this).val().trim() === '') {
	  				  $(this).parent().addClass('alert-style');
	  				  isValid=false;
	  			  }
	  		});
	  		let value = $(".triple-package-revision").find(".revision-placeholder").text(); 
    		if(isNaN(value)){
    			$(".triple-package-revision").addClass('alert-style');
    			isValid=false;
    		}
    	}
    	
    	// 32,000 -> 32000 으로 포맷 변경
    	function numberFormatParsing(formattedNumber){
    		return Number(formattedNumber.replace(/,/g, ''));
    	}
    	
    	
    	
    	//추가 옵션에 대한 List객체 생성
    	function createOptionList(){
    		//둘중 하나라도 체크된경우 옵션에 대한 객체 생성후 값들 반환
    		if($("#leadtime").is(":checked") || $("#add_revision").is(":checked")){
    			let projectAddOptionList = [];
    			//"빠른 작업" 추가
    			if($("#leadtime").is(":checked")){
        			let options={
        					"add_option_name" : $("#leadtime_add_option_name").text(),
        					"add_option_description" : $("#leadtime_add_option_description").text(),
        					"add_option_price" : numberFormatParsing($("#leadtime_add_option_price").val())
        				}	
        			projectAddOptionList.push(options);
        		}
    			//"추가 수정" 체크
        		if($("#add_revision").is(":checked")){
        			let options={
        					"add_option_name" : $("#revision_add_option_name").text(),
        					"add_option_description" : $("#revision_add_option_description").text(),
        					"add_option_price" : numberFormatParsing($("#revision_add_option_price").val())
        			}
        			projectAddOptionList.push(options);
        		}
        		return projectAddOptionList;
    		}else{//하나도 체크안된경우 null 반환
    			return null;
    		}
    	}
    	//단일패키지 입력값 
    	function singlePacakgeInputs(){
    		//보낼 데이터들
    		let pkg_title = $("#pkg_title").val();
    		let pkg_description = $("#pkg_description").val();
    		let price = numberFormatParsing($("#price").val());
    		let revision = $("#revision").val();
    		let workdays = $("#workdays").val();
    		let projectAddOptionList = createOptionList();
    		/*console.log("pkg_title" , pkg_title);
    		console.log("pkg_description" , pkg_description);
    		console.log("price" , price);
    		console.log("workdays" , workdays);
    		console.log("projectAddOptionMap" , projectAddOptionMap);*/
    		
    		let requestData = {//FormData는 직접 리스트 전송 불가(Multipart는 가능한듯?)
    				"pkg_title":pkg_title,
    				"pkg_description":pkg_description,
    				"price":price,
    				"revision":revision,
    				"workdays":workdays,
    				"projectAddOptionList":projectAddOptionList
    		};
    		
    		return requestData;
    	} 
    	// 삼단 패키지 입력값들
    	function triplePacakgeInputs(){
    		//보낼 데이터들 
    		//베이직 패키지
    		let basic_pkg_title = $("#basic_pkg_title").val();
    		let basic_pkg_description = $("#basic_pkg_description").val();
    		let basic_price = numberFormatParsing($("#basic_price").val());
    		let basic_revision = $("#basic_revision").val();
    		let basic_workdays = $("#basic_workdays").val();
    		
    		//이코노미 패키지
    		let economy_pkg_title = $("#economy_pkg_title").val();
    		let economy_pkg_description = $("#economy_pkg_description").val();
    		let economy_price = numberFormatParsing($("#economy_price").val());
    		let economy_revision = $("#economy_revision").val();
    		let economy_workdays = $("#economy_workdays").val();
    		
    		//프리미엄 패키지
    		let premium_pkg_title = $("#premium_pkg_title").val();
    		let premium_pkg_description = $("#premium_pkg_description").val();
    		let premium_price = numberFormatParsing($("#premium_price").val());
    		let premium_revision = $("#premium_revision").val();
    		let premium_workdays = $("#premium_workdays").val();
    		
    		let projectAddOptionList = createOptionList();
    		/*console.log("pkg_title" , pkg_title);
    		console.log("pkg_description" , pkg_description);
    		console.log("price" , price);
    		console.log("workdays" , workdays);
    		console.log("projectAddOptionMap" , projectAddOptionMap);*/
    		
    		let requestData = {//FormData는 직접 리스트 전송 불가(Multipart는 가능한듯?)
    				"basic_pkg_title":basic_pkg_title,
    				"basic_pkg_description":basic_pkg_description,
    				"basic_price":basic_price,
    				"basic_revision":basic_revision,
    				"basic_workdays":basic_workdays,
    				
    				"economy_pkg_title":economy_pkg_title,
    				"economy_pkg_description":economy_pkg_description,
    				"economy_price":economy_price,
    				"economy_revision":economy_revision,
    				"economy_workdays":economy_workdays,
    				
    				"premium_pkg_title":premium_pkg_title,
    				"premium_pkg_description":premium_pkg_description,
    				"premium_price":premium_price,
    				"premium_revision":premium_revision,
    				"premium_workdays":premium_workdays,
    				
    				"projectAddOptionList":projectAddOptionList
    		};
    		
    		return requestData;
    	} 
    	
    	if(!isValid){//필수 사항 미 완성 -> 스크롤이 맨 위로 올라감
    		$('html, body').animate({ scrollTop: 0 }, "0.5s");
//    		console.log();
    		
    	}else{//전부 채워넣은경우 -> 데이터 전송
    		if(switchMode==="off"){//싱글패키지
        		//보낼 데이터들
        		let requestData = singlePacakgeInputs();
        		
        		//ajax통신
        		$.ajax({
    				url:"/metahaus/project/single-package-ajax",
    				type:"post",
    				processData:false,
    				contentType:"application/json; charset=UTF-8",
    				dataType : 'text',
    				cache:false,
    				data:JSON.stringify(requestData),
    				success:function(nextPageURL){
    					//nextPageURL = /metahaus/project/forms/preview
    					window.location.href = nextPageURL;
    				},
    				error:function(request,status,error){
    				        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    			    }
    			});
        		
    		}else{//삼단패키지
    			//보낼 데이터들
        		let requestData = triplePacakgeInputs();
        		
        		//ajax통신
        		$.ajax({
    				url:"/metahaus/project/triple-package-ajax",
    				type:"post",
    				processData:false,
    				contentType:"application/json; charset=UTF-8",
    				dataType : 'text',
    				cache:false,
    				data:JSON.stringify(requestData),
    				success:function(nextPageURL){
    					//nextPageURL = /metahaus/project/forms/preview
    					window.location.href = nextPageURL;
    				},
    				error:function(request,status,error){
    				        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    			    }
    			});
    		}
    		
    	}
    });
    
 // '취소' 누른다음 취소 누른경우 -> 로컬스토리지 비워줌
    $(".cancel-modal").on("click", function(){
    	window.localStorage.clear();
    	window.location.href='/metahaus/project/my-products?pageNo=0';
    });
    
    //다시 값을 입력하면 alert-style 사라짐
    $("textarea").on("input",function(){
    	if($(this).val()!==""){
    		$(this).parent().parent().removeClass("alert-style");
    	}
    });
    $("input").on("input",function(){
    	if($(this).val()!==""){
    		$(this).parent().removeClass("alert-style");
    	}
    });
    $(".single-package-revision").on("click",function(){
    	$(".single-package-revision").removeClass('alert-style');
    });
});
