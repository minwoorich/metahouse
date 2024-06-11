///////////////project_product_list 시작/////////////////////////

$(document).ready(function () {

    //옵션 드롭다운
    $(".project-product-option").click(function () {
        $(this).find(".project-product-option-dropdown").toggle();
    });


    //옵션 드롭다운 - 삭제하기
    $(".project-product-option-dropdown li button").click(function (event) {
    	let deleteForm = $(this).closest("form");
        event.preventDefault();
        //모달에서 delete누르면 form 수행
        $("#deleteProjectModal .delete").click(function(){
        	deleteForm.submit();
        })
    });



    //카드 클릭 -> 상세보기 페이지로 이동
    $(".project-product-card").on("click", function (event) {
        const target = event.target;
        // 구인 글 고유번호 추출 + 컨트롤러 전송
        //...
        //window.location.href = 
    });
});

////////////////////////project_produce_list (구인관리) 끝///////////////////////////
