///////////////mypage_asset_product_list (구인관리) 시작/////////////////////////


$(document).ready(function () {

    //옵션 드롭다운
    $(".asset-product-option").click(function () {
        $(this).find(".asset-product-option-dropdown").toggle();
    });


    //옵션 드롭다운 - 삭제하기
    $(".asset-product-option-dropdown li button").click(function (event) {
        let deleteForm = $(this).closest("form");
        event.preventDefault();
        //모달에서 delete누르면 form 수행
        $("#deleteAssetModal .delete").click(function(){
            deleteForm.submit();
        })
    });

    
    //카드 클릭 -> 상세보기 페이지로 이동
    $(".asset-product-card").on("click", function (event) {
        const target = event.target;
    });
});

////////////////////////mypage_asset_produce_list (구인관리) 끝///////////////////////////
