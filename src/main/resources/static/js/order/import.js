function iamport(){

    var flag = $("#flag").val();
    var principalId = $("#principalId").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var postcode = $("#postcode").val();
    var address = $("#address").val() + " " + $("#detailAddress").val();

    var productName;
    var productId = $("#productId").val();
    var detailName = $("#productName").val();
    var cartName = $("#cartName").val();
    var amount = $("#amount").val();
    var price = $("#total-price").text();


    //가맹점 식별코드
    IMP.init("imp38164824");
    IMP.request_pay({
        pg : 'kcp',
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(), //주문번호
        name : productName,
        amount : price, //숫자 타입
        buyer_email : email,
        buyer_name : name,
        buyer_tel : phone,
        buyer_addr : address,
        buyer_postcode : postcode
    }, function(res) {// callback
        //rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.

        // 결제검증
        $.ajax({
            type : "POST",
            url : "/verifyIamport/" + res.imp_uid
        }).done(function(data) {

            if(res.paid_amount == data.response.amount){
                alert("결제 및 결제검증완료");

                //결제 성공 시 비즈니스 로직
                orderProcess(req_data, res.imp_uid);//비즈니스 로직

            } else {
                alert("결제 실패");
                //환불 요청
                cancelPayment(res.imp_uid);
            }
        });
    });
}

//주문 비즈니스 로직
function orderProcess(req_data, imp_uid){
    $.ajax({
        type: "POST",
        url: `/api/order`,
        data:JSON.stringify(req_data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (rsp) {
            alert("결제되었습니다.");
	location.href = "/mypage/"+rsp.data;

        },
        error: function (xhr) {
            var errorResponse =JSON.parse(xhr.responseText);
            var errorMessage = errorResponse.errorMessage;
            alert(errorMessage);

            // 결제 취소 요청
            cancelPayment(imp_uid);
        }
    });
}