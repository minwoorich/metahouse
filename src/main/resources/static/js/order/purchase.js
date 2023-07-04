$(document).ready(function(){
	//up-down 수량 조절파트
	$("input:checkbox").siblings().children("button").hide();

	$("input:checkbox").on('click', function() {
		if($(this).prop('checked')){
			$(this).siblings().children("button").show();
			$(this).siblings().children("input").val("1");
		} else {
			$(this).siblings().children("button").hide();
			$(this).siblings().children("input").val("0");
		}
	});
	
	$(".up").on("click", function(){
		const $target = $(event.target); //이벤트 발생한 요소
		const display = $target.siblings(".updown_display");
		const value = display.val();
		if(value==10){
			alert("추가옵션 수량이 과다할 경우 의뢰가 취소될 수 있습니다.")
			$target.siblings(".updown_display").val(parseInt(value) + 1);
		}else{ 
			$target.siblings(".updown_display").val(parseInt(value) + 1);
		}
	});
	$(".down").on("click", function(){
		const $target = $(event.target); //이벤트 발생한 요소
		const display = $target.siblings(".updown_display");
		const value = display.val();
		if(value==1){
			alert("최소 1개 이상 선택하여야 합니다.")
		}else{ 
			$target.siblings(".updown_display").val(parseInt(value) - 1); //해당값을 적용해준다.
		}
	});
	
	//사용자가 입력한 글자수 카운팅
    $("textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().find(".counter");
        
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
    });
	
//  에셋 구매버튼
    $(".AssetBtn").on("click", function(){
    	var param = {"assetOrder" :{
    		"asset_id" : $("#Aid").attr("value"),
    		"buyer_id2" : $("#Uid").attr("value")
    		},
    		"consumeAmount" : $("#consumeAmount").text().replace(/,/g, "")
    	}
    	$.ajax({
    		url : "/metahaus/order/asset",
    		type : 'post',
    		data : JSON.stringify(param),
    		contentType: 'application/json',
    		success : function() {
    			alert("구매가 완료되었습니다.");
    			location.href = "/metahaus/order/asset/buylist";
    	     },
    		error : function() {
    			alert("error");
    		}
    	});
    });
/*------------------------------- 프로젝트 구매 가격 자동계산+구매완료 -----------------------------*/   
	var SP = $("#select_price span").text();
	//http://localhost:8088/metahaus/point/charge 포인트 충전하러가기
	//추가금액 계산&표시: 수량 표시부분이 readonly라 값을 가져오기위해서 추가 함수처리 해야됨
	var count1 = 0;
	var count2 = 0;
	var p1 = 0
	var p2 = 0; 
	var totalAddprice = 0;		
	var TP = 0;
	$("#count0").on('input', function() {
	    count1 = $(this).val();
	    p1 = $("#P0").attr("value")*count1;
	    totalAddprice = p1 + p2;
		TP = parseInt(SP)+parseInt(totalAddprice);
		$("#price_totalOption").children().remove();
    	$("#price_totalOption").append("<span>"+totalAddprice+"원</spna>");
    	$("#consumeP").children().remove();
    	$("#consumeP").append("<span>"+TP+"원</spna>");
	});
	$("#count1").on('input', function() {
	    count2 = $(this).val();
	    p2 = $("#P1").attr("value")*count2;
	    totalAddprice = p1 + p2;
	    TP = parseInt(SP)+parseInt(totalAddprice);
		$("#price_totalOption").children().remove();
    	$("#price_totalOption").append("<span>"+totalAddprice+"원</spna>");
    	$("#consumeP").children().remove();
    	$("#consumeP").append("<span>"+TP+"원</spna>");
	});
	
	(function ($) {
	    var originalVal = $.fn.val;
	    $.fn.val = function (value) {
	        var res = originalVal.apply(this, arguments);
	 
	        if (this.is('input:text') && arguments.length >= 1) {
	            // this is input type=text setter
	            this.trigger("input");
	        }
	 
	        return res;
	    };
	})(jQuery);
	
	//3단 패키기 가격 정보 출력
	$("#tp").on("change", function(){
		$.ajax({
			url:"/metahaus/project/package/price",
			type:"post",
			data: {
				projectNum : $("#Aid_pj").attr("value")
				},
			dataType:"json",
			success:function(data){
				var index = $("#tp option").index($("#tp option:selected"));
				SP = $("#tp option:selected").val();
				
				let item = null;
				switch (index) {
				  case 0:
					item = '<div class="price_package_info">제목<br/><br/>'+ data.basic_pkg_title +'</div>';
					item += '<div class="price_package_info" id="price_package_explain">설명<br/><br/>'+ data.basic_pkg_description +'</div>';
					item += '<div class="price_package_info">작업일<br/><br/>'+ data.basic_workdays +'일</div>';
					item += '<div class="price_package_info">가격<br/><br/>'+ data.basic_price +'원</div>';
				    break;
				  case 1:
					item = '<div class="price_package_info">제목<br/><br/>'+ data.economy_pkg_title +'</div>';
					item += '<div class="price_package_info" id="price_package_explain">설명<br/><br/>'+ data.economy_pkg_description +'</div>';
					item += '<div class="price_package_info">작업일<br/><br/>'+ data.economy_workdays +'일</div>';
					item += '<div class="price_package_info">가격<br/><br/>'+ data.economy_price +'원</div>';
				    break;
				  default:
					item = '<div class="price_package_info">제목<br/><br/>'+ data.premium_pkg_title +'</div>';
					item += '<div class="price_package_info" id="price_package_explain">설명<br/><br/>'+ data.premium_pkg_description +'</div>';
					item += '<div class="price_package_info">작업일<br/><br/>'+ data.premium_workdays +'일</div>';
					item += '<div class="price_package_info">가격<br/><br/>'+ data.premium_price +'원</div>';
				}
				//패키지별 정보 추가
				$("#priceData").children().remove();
		    	$("#priceData").append(item);
		    	//선택패키지 가격 정보
				$("#select_price").children().remove();
		    	$("#select_price").append('<span>'+ SP +'원</span>');
		    	//기본+옵션 가격 총합
		    	TP = parseInt(SP)+parseInt(totalAddprice);
		    	$("#consumeP").children().remove();
		    	$("#consumeP").append("<span>"+TP+"원</spna>");
			},
			error:function(result) {
                alert("error");
            }
			
		});
	});
//프로젝트 구매버튼 (null, #{order_id}, #{add_option_id}, #{count}) / (null, #{project_id}, #{buyer_id}, "주문요청", now(), #{request}, #{order_price})
	$(".PjtBtn").on("click", function(){
    	var param = {"projectOrder" : {
    					"project_id" : $("#Aid_pj").attr("value"), 
    					"buyer_id" : $("#U_id").attr("value"),
    					"request" : $(".request").val(),
    					"order_price" : $("#tp option:selected").val()
    					},
    				 "options" : [{
    					 	"add_option_id" : $("input[type=checkbox][id=ADid0]:checked").val(),
    					 	"count" : $("#count0").val()
    				 	},
    				 	{
    				 		"add_option_id" : $("input[type=checkbox][id=ADid1]:checked").val(),
    				 		"count" : $("#count1").val()
    				 	}
    				 ],
    				 "consumeAmount" : parseInt(SP)+parseInt(totalAddprice)
    				}
    	$.ajax({
    		url : "/metahaus/order/project",
    		type : 'post',
    		data : JSON.stringify(param),
    		contentType: 'application/json',
    		success : function() {
    			alert("구매가 완료되었습니다.");
    			location.href = "/metahaus/order/project/buylist";
    		},
    		error : function() {
    			alert("error");
    		}
    	});
    });
    
})