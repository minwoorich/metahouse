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
 
    // 휴대폰 번호 자동 하이픈
    $(".autoHyphen").on("input", function (event) {    	
    	$(this).val( $(this).val()
    			.replace(/[^0-9]/g, "")
    			.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3")
    			.replace("--", "-") );
    });
    
    $(".myBtn").on("click", function(){
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
})