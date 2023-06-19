$(document).ready(function () {
    // 맨처음 시작할때 이미지 카운터
    const mainImgCnt = $(".main-img").length;
    const option1Cnt = $(".option1-img").length;
    const option2Cnt = $(".option2-img").length;
    const option3Cnt = $(".option3-img").length;
    $(".main-img-counter").text(mainImgCnt);
    $(".option1-img-counter").text(option1Cnt);
    $(".option2-img-counter").text(option2Cnt);
    $(".option3-img-counter").text(option3Cnt);
    

    // 맨 처음 시작할 때 이미지 추가 버튼 상태
    const mainImgMax = 1;
    const optionalMax = 3;
    if ($(".main-img").length == mainImgMax) {
        $(".main-img-add").addClass("full");
    } else {
        $(".main-img-add").removeClass("full");
    }
    if ($(".option1-img").length == optionalMax) {
        $(".option1-add").addClass("full");
    } else {
        $(".option1-add").removeClass("full");
    }
    if ($(".option2-img").length == optionalMax) {
        $(".option2-add").addClass("full");
    } else {
        $(".option2-add").removeClass("full");
    }
    if ($(".option3-img").length == optionalMax) {
        $(".option3-add").addClass("full");
    } else {
        $(".option3-add").removeClass("full");
    }
    
    // 사진 호버하면 삭제 표시
    $(".image-frame").hover(
        function () {
            const coverImage =
                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
                    "<div>" +
                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0);'></i>" +
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
            if ($(".main-img").length == mainImgMax) {
                $(".main-img-add").addClass("full");
            } else {
                $(".main-img-add").removeClass("full");
            }
            
            if ($(".option1-img").length == optionalMax) {
                $(".option1-add").addClass("full");
            } else {
                $(".option1-add").removeClass("full");
            }
            
            if ($(".option2-img").length == optionalMax) {
                $(".option2-add").addClass("full");
            } else {
                $(".option2-add").removeClass("full");
            }
            
            if ($(".option3-img").length == optionalMax) {
                $(".option3-add").addClass("full");
            } else {
                $(".option3-add").removeClass("full");
            }
            //사진 갯수 카운터
            $(".main-img-counter").text($(".main-img").length);
            $(".option1-img-counter").text($(".option1-img").length);
            $(".option2-img-counter").text($(".option2-img").length);
            $(".option3-img-counter").text($(".option3-img").length);
        });
    });
    
    $("#protfolio_content_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text1-counter");
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
    
    $("#protfolio_content_point_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text2-counter");
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
    
    $("#protfolio_content_style_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text3-counter");
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
        
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
        
    });
    
    $("#portfolio_img_insert").on("change", function() {
		let insertImg = 
			$("<div class='image-frame main-img'>"
			+ "<img src= '" + window.URL.createObjectURL(this.files[0]) + "' alt='대표 사진'>" 
			+ "</div>")
		for (var i = 0; i < $("#portfolio_img_insert")[0].files.length; i++) {
			$("#main_img_parent").prepend(insertImg)
		}
		//$("#main_img_parent").prepend(insertImg)
		
		if ($(".main-img").length == mainImgMax) {
		    $(".main-img-add").addClass("full");
		} else {
		    $(".main-img-add").removeClass("full");
		}
		if ($(".option1-img").length == optionalMax) {
		    $(".option1-add").addClass("full");
		} else {
		    $(".option1-add").removeClass("full");
		}
		if ($(".option2-img").length == optionalMax) {
		    $(".option2-add").addClass("full");
		} else {
		    $(".option2-add").removeClass("full");
		}
		if ($(".option3-img").length == optionalMax) {
		    $(".option3-add").addClass("full");
		} else {
		    $(".option3-add").removeClass("full");
		}
		
		//사진 갯수 카운터
        $(".main-img-counter").text($(".main-img").length);
        $(".option1-img-counter").text($(".option1-img").length);
        $(".option2-img-counter").text($(".option2-img").length);
        $(".option3-img-counter").text($(".option3-img").length);
		
		// 사진 호버하면 삭제 표시
	    $(".image-frame").hover(
	        function () {
	            const coverImage =
	                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
	                    "<div>" +
	                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0);'></i>" +
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
	            $("#portfolio_img_insert").val('');
	            //등록된 사진개수가 한도 까지 등록된경우 추가 버튼 사라짐
	            if ($(".main-img").length == mainImgMax) {
	                $(".main-img-add").addClass("full");
	            } else {
	                $(".main-img-add").removeClass("full");
	            }
	            
	            if ($(".option1-img").length == optionalMax) {
	                $(".option1-add").addClass("full");
	            } else {
	                $(".option1-add").removeClass("full");
	            }
	            
	            if ($(".option2-img").length == optionalMax) {
	                $(".option2-add").addClass("full");
	            } else {
	                $(".option2-add").removeClass("full");
	            }
	            
	            if ($(".option3-img").length == optionalMax) {
	                $(".option3-add").addClass("full");
	            } else {
	                $(".option3-add").removeClass("full");
	            }
	            //사진 갯수 카운터
	            $(".main-img-counter").text($(".main-img").length);
	            $(".option1-img-counter").text($(".option1-img").length);
	            $(".option2-img-counter").text($(".option2-img").length);
	            $(".option3-img-counter").text($(".option3-img").length);
	        });
	    });
    }); 
    
    // 여러개 파일
    $("#portfolio_img_content_insert").on("change", function() {

		if (this.files && this.files.length > 0) {
			for (var i = 0; i < this.files.length; i++) {
				var reader = new FileReader();
				reader.onload = function(e) {
					var imageDiv = $("<div>").addClass("image-frame main-img");
					var img = $("<img>").attr("src", e.target.result).attr("alt", "내용 사진");
					imageDiv.append(img);

					$("#content_img_parent").prepend(imageDiv);
				};
				reader.readAsDataURL(this.files[i]);
			}
		}
		
		if ($(".main-img").length == mainImgMax) {
		    $(".main-img-add").addClass("full");
		} else {
		    $(".main-img-add").removeClass("full");
		}
		if ($(".option1-img").length == optionalMax) {
		    $(".option1-add").addClass("full");
		} else {
		    $(".option1-add").removeClass("full");
		}
		if ($(".option2-img").length == optionalMax) {
		    $(".option2-add").addClass("full");
		} else {
		    $(".option2-add").removeClass("full");
		}
		if ($(".option3-img").length == optionalMax) {
		    $(".option3-add").addClass("full");
		} else {
		    $(".option3-add").removeClass("full");
		}
		
		//사진 갯수 카운터
        $(".main-img-counter").text($(".main-img").length);
        $(".option1-img-counter").text($(".option1-img").length);
        $(".option2-img-counter").text($(".option2-img").length);
        $(".option3-img-counter").text($(".option3-img").length);
		
		// 사진 호버하면 삭제 표시
	    $(".image-frame").hover(
	        function () {
	            const coverImage =
	                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
	                    "<div>" +
	                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0);'></i>" +
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
	            $("#portfolio_img_content_insert").val('');
	            //등록된 사진개수가 한도 까지 등록된경우 추가 버튼 사라짐
	            if ($(".main-img").length == mainImgMax) {
	                $(".main-img-add").addClass("full");
	            } else {
	                $(".main-img-add").removeClass("full");
	            }
	            
	            if ($(".option1-img").length == optionalMax) {
	                $(".option1-add").addClass("full");
	            } else {
	                $(".option1-add").removeClass("full");
	            }
	            
	            if ($(".option2-img").length == optionalMax) {
	                $(".option2-add").addClass("full");
	            } else {
	                $(".option2-add").removeClass("full");
	            }
	            
	            if ($(".option3-img").length == optionalMax) {
	                $(".option3-add").addClass("full");
	            } else {
	                $(".option3-add").removeClass("full");
	            }
	            //사진 갯수 카운터
	            $(".main-img-counter").text($(".main-img").length);
	            $(".option1-img-counter").text($(".option1-img").length);
	            $(".option2-img-counter").text($(".option2-img").length);
	            $(".option3-img-counter").text($(".option3-img").length);
	        });
	    });
    }); 

});

