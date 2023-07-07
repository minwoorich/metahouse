var photo = document.getElementById("photo");
var thumbnail = document.querySelectorAll("#gallery > li > img");

for ( var i = 0; i < thumbnail.length; i++ )
  thumbnail[i].addEventListener("click", function () {

	photo.setAttribute("src", this.getAttribute("src"));

  });

//리뷰 답글 달기
$("#writeComment").on("click", function(){
	var reviewId = $(this).siblings("#P-review-id").val();
	var commentWriter = $('.seller_id').text();
	var commentText = $('#commentText').val();

	var param = {"comment" :{
			"review_id" : reviewId,
			"comment_writer_id" : commentWriter, 
			"comment_text" : commentText
    	},
    	"tag" : 'PJT'
	}
	if(commentText===''){
		alert("내용을 입력해주세요")
	}else{
		var con = confirm("작성하신 후 수정/삭제가 불가합니다. 등록하시겠습니까?")
		if(con){
		  $.ajax({      
			  url : "/metahaus/reviewComment",      
			  type : "POST",  
			  data : JSON.stringify(param),
			  contentType: 'application/json',
			  success : function(){   
			        alert("작성이 완료되었습니다.");
			  		window.location.reload()       
			        },   
			    error : function(){  
			    	alert("error");  
			        }  
			}); 
		}
	}
});
//리뷰 이미지 넘기기였나..?
let test = document.querySelector("#color-test");
function next(el) {
    let list = el.parentNode.querySelector(".banner-slider-list");

    let listSize = list.offsetWidth;
    let itemSize = list.firstElementChild.offsetWidth;
    let countOfView = parseInt(listSize / itemSize);



    let index = Number(list.getAttribute("index"));
    let itemCount = list.childElementCount;

    if (index + countOfView >= itemCount) {
        return;
    }
    index++;
    list.setAttribute("index", index);


    list.style.left = -itemSize * index + "px";

    colorChange(index);
}

function pre(el) {
    let list = el.parentNode.querySelector(".banner-slider-list");

    let listSize = list.offsetWidth;
    let itemSize = list.firstElementChild.offsetWidth;
    let countOfView = parseInt(listSize / itemSize);



    let index = Number(list.getAttribute("index"));
    let itemCount = list.childElementCount;

    if (index <= 0) {
        list.style.left = 0;
        list.setAttribute("index", 0);
        return;
    }
    index--;
    list.setAttribute("index", index);

    list.style.left = -itemSize * index + "px";

    colorChange(index);
}


document.addEventListener('DOMContentLoaded', function() {
    var leftHalf = document.querySelector('.left-half');
    var rightHalf = document.querySelector('.right-half');

    leftHalf.addEventListener('click', function() {
      window.location.href = leftHalf.href;
    });

    rightHalf.addEventListener('click', function() {
      window.location.href = rightHalf.href;
    });
  });