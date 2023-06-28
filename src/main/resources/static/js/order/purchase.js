$(document).ready(function(){
	//up-down
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
    	var param = {"asset_id" : $("#Aid").attr("value"), "buyer_id2" : $("#Uid").attr("value")}
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
//   프로젝트 구매버튼 (null, #{order_id}, #{add_option_id}, #{count}) / (null, #{project_id}, #{buyer_id}, "주문요청", now(), #{request}, #{order_price})
    $(".PjtBtn").on("click", function(){
    	var param = {"projectOrder" : {
    					"project_id" : $("#Aid_pj").attr("value"), 
    					"buyer_id" : $("#U_id").attr("value"),
    					"request" : $(".request").val(),
    					"order_price" : $("#tp option:selected").val()
    					},
    				 "option" : {
    					 "add_option_id" : $("input[type=checkbox][name=add_option]:checked").val(),
    					 "count" : $(".updown_display").val()
    				 }
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