


$(document).ready(function () {
    // 맨처음 시작할때 이미지 카운터
    const thumbnailCnt = $(".thumbnail-img").length;
    const optionalCnt = $(".optional-img").length;
    $(".thumbnail-img-counter").text(thumbnailCnt);
    $(".optional-img-counter").text(optionalCnt);

    // 맨 처음 시작할 때 이미지 추가 버튼 상태
    const thumbnailMax = 1;
    const optionalMax = 6;
    if ($(".thumbnail-img").length == thumbnailMax) {
        $(".thumbnail-add").addClass("full");
    } else {
        $(".thumbnail-add").removeClass("full");
    }

    if ($(".optional-img").length == optionalMax) {
        $(".optional-add").addClass("full");
    } else {
        $(".optional-add").removeClass("full");
    }

    //사용자가 입력한 글자수 카운팅
    $(".form-item-description").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".counter");
        const $cousin = $target.parent().siblings().children().children();
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        // console.log($length+ "맥스 : "+$maxLength);
        if ($length == $maxLength) {
            $counter.addClass("max-length");
        } else {
            $counter.removeClass("max-length");
        }
    });


    // 작업기간 입력폼에 0~90 까지만 입력 가능하게 하는 코드

    $('input[type="number"]').on('input', function (event) {
        const value = $(this).val();
        const alertMsg = $("<div>0~90 만 입력해주세요</div>").attr("class", "alert-msg");

        if (value < 0 || value > 90) {
            $(this).addClass("alert-style");
            $(this).after(alertMsg);
        } else {
            $(this).removeClass("alert-style");
            $(this).next(".alert-msg").remove();
        }
    });


    // 사진 호버하면 삭제 표시


    $(".image-frame").hover(
        function () {
            const coverImage =
                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
                    "<div>" +
                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0,0.5);'></i>" +
                    "<div/>" +
                    "</button>").attr("class", "delete-cover");
            $(this).append(coverImage);
        },
        function () {
            $(this).find(".delete-cover").remove();
        }
    )


    //사진 누르면 삭제 모달뜨고 버튼 누르면 삭제완료됨

    const deleteBtn = $("#delete-img-Modal").find("button");
    //삭제할 사진을 누르면 해당 사진 객체 저장 + 모달 뜸
    $(".image-frame").on("click", function (event) {
        const deleteTarget = $(this);
        //모달 버튼 누르면 삭제
        deleteBtn.on("click", function () {
            deleteTarget.remove();
            //등록된 사진개수가 한도 까지 등록된경우 추가 버튼 사라짐
            if ($(".thumbnail-img").length == thumbnailMax) {
                $(".thumbnail-add").addClass("full");
            } else {
                $(".thumbnail-add").removeClass("full");
            }

            if ($(".optional-img").length == optionalMax) {
                $(".optional-add").addClass("full");
            } else {
                $(".optional-add").removeClass("full");
            }
            //사진 갯수 카운터
            $(".thumbnail-img-counter").text($(".thumbnail-img").length);
            $(".optional-img-counter").text($(".optional-img").length);
        });
    });


    


    

});

