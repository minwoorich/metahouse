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
	
	/*//텍스트 입력 할 때 높이 자동 조절
	$('textarea').on('input', function() {
		let target=null;
		if($(this).hasClass("single-textarea-title")){
			target = $(".single-textarea-title");
		}else if($(this).hasClass("single-textarea-description")){
			target = $(".single-textarea-description");
		}else if($(this).hasClass("triple-textarea-title")){
			target = $(".triple-textarea-title");
		}else{
			target = $(".triple-textarea-description");
		}
		target.css('height', $(this).prop('scrollHeight') + 'px');
	});
	// 텍스트 지울때 높이 자동 조절
	$('textarea').on('keydown', function(event) {
	  // Backspace 키를 누를 때에도 높이를 조정
	  if (event.key === 'Backspace') {
	    $(this).css('height', 'auto'); // 초기 높이를 자동으로 설정
	    $(this).css('height', $(this).prop('scrollHeight') + 'px'); // 내용에 맞게 높이 조정
	  }
	});*/
	
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
    
    // 가격 입력(추가옵션 + 패키지 가격) 했을때 경고 메시지
    $('input[type="number"]').on('input', function (event) {
        const value = $(this).val();
        const alertMsg = $("<div>5,000~ 999,999,999 원 까지 만 입력 가능합니다</div>").attr("class", "alert-msg");
        
        if (value < 5000 || value > 999999999) {
        	$(this).parent().next(".alert-msg").remove();
            $(this).parent().addClass("alert-style");
            $(this).parent().after(alertMsg);
        } else{
        	$(this).parent().removeClass("alert-style");
            $(this).parent().next(".alert-msg").remove();
        }
        if(value===""){
        	$(this).parent().removeClass("alert-style");
            $(this).parent().next(".alert-msg").remove();
        }
    });
    
    //추가 옵션 설정
    $(".option-box-row01 input").on("change",function(){
    	
    	if($(this).is(":checked")){
    		$(this).parent().siblings(".option-box-row02").removeClass("deactivate");
    	}else{
    		$(this).parent().siblings(".option-box-row02").addClass("deactivate");
    	}
    });
    
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
    	}
    	
    	//전부 채웠으면 다음 페이지 이동
    	if(isValid){
    		location.href='/mh/mypage/project-form-03';
    	}else{//못채웠으면 모달 출력
    		$('html, body').animate({ scrollTop: 0 }, "0.5s");
    	}
    });
    
    //
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
	
});
