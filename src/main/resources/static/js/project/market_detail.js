var photo = document.getElementById("photo");
var thumbnail = document.querySelectorAll("#gallery > li > img");

for ( var i = 0; i < thumbnail.length; i++ )
  thumbnail[i].addEventListener("click", function () {

	photo.setAttribute("src", this.getAttribute("src"));

  });

//리뷰 답글 달기
$(".writeComment").on("click", function(){
	var reviewId = $(this).siblings(".P-review-id").val();
	var commentWriter = $('.seller_id').text();
	var commentText = $(this).siblings('.commentText').val();

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
