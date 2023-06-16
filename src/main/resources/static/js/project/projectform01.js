
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
        
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
    });


    // 사진 호버하면 삭제 표시
    $(".image-frame").hover(
    	//마우스 진입
        function () {
            const coverImage =
                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
                    "<div>" +
                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0,0.5);'></i>" +
                    "<div/>" +
                    "</button>").attr("class", "delete-cover");
            $(this).append(coverImage);
        },
        //마우스 아웃
        function () {
            $(this).find(".delete-cover").remove();
        }
    )
    
    //이미지 추가버튼 누르면 미리보기 생성 - 대표이미지
    $("#thumbnail").on("click",function(){
    	let file = $(this)[0].files[0];
    	if(file){//파일이 존재한다면
    		alert("파일미리보기 성공");
    		//업로드할 이미지의 경로(미리보기용)
    		let fileURL = URL.createObjectURL(file);
    		//미리보기할 이미지를 담은 요소
    		let element = "<div class='image-frame thumbnail-img'>"+
							"<img src='"+fileURL +"'alt='대표 사진'>"+
							"</div>"
    		$("#thumbnail-form").append(element);
    	}else{
    		alert("파일이 존재하지 않습니다!");
    	}
    });

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

    // "다음" 버튼 누른경우 - 
    $(".form-buttons-next").on("click",function(){
    	$(this).preventDefault();
    	let isValid = true;
    	const alertMsg = $("<div>항목을 채워주세요</div>").attr("class", "alert-msg");
    	
    	
    	$('textarea[required]').each(function() {
			  if ($(this).val().trim() === '') {
				  $(this).parent().parent().addClass('alert-style');
				  isValid=false;
			  }
    	});
    	if($(".thumbnail-img").length == 0){
    		$(".thumbnail-form").addClass("alert-style");
    	}
    	if(!isValid){//필수 사항 미 완성 -> 스크롤이 맨 위로 올라감
    		$('html, body').animate({ scrollTop: 0 }, "0.5s");
    	}else{//전부 채워넣은경우 -> submit 동작 수행
    		$(this).submit();
    	}
    	
  	});
    
    //textarea에 값을 입력하면 alert-style 사라짐
    $("textarea").on("input",function(){
    	if($(this).val()!==""){
    		$(this).parent().parent().removeClass("alert-style");
    	}
    });
    
    //사진 추가하면 alsert-style 사라짐
    if($(".thumbnail-img").length !== 0){
		$(".thumbnail-form").removeClass("alert-style");
	}
	
    


});

