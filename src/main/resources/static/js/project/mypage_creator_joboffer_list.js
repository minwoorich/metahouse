///////////////mypage_creator_joboffer_list (구인관리) 시작/////////////////////////

//신청현황 드롭다운
$(document).ready(function () {
    $(".my-dropdown button").click(function () {
        $(this).siblings(".my-dropdown-contents").toggle();
    });


    //옵션 드롭다운
    $(".job-offer-option").click(function () {
        $(this).find(".job-offer-option-dropdown").toggle();
    });


    //옵션 드롭다운 - 삭제하기
    $(".job-offer-option-dropdown li a").click(function () {
        $(this)
    });

    //카드 호버
    /*$(".job-offer-card").mouseover(function (event) {
        event.stopPropagation();
        var target = $(event.target); // 이벤트가 발생한 요소
        $(target).css({
            "background-color": "rgba(0,0,0,0.1)",
            "transition": "0.5s"
        });

    }).mouseout(function (event) {
        event.stopPropagation();
        var target = $(event.target); // 이벤트가 발생한 요소
        $(target).css("background-color", ""); // 원래 상태로 돌아가는 예시 (배경색 제거)

    });*/


    //카드 클릭 -> 상세보기 페이지로 이동
    $(".job-offer-card").on("click", function (event) {
        const target = event.target;
        // 구인 글 고유번호 추출 + 컨트롤러 전송
        //...
        //window.location.href = 
    });
});

////////////////////////mypage_creator_joboffer_list (구인관리) 끝///////////////////////////
