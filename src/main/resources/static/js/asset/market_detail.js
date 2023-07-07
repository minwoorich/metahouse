var photo = document.getElementById("photo");
var thumbnail = document.querySelectorAll("#gallery > li > img");

for ( var i = 0; i < thumbnail.length; i++ )
  thumbnail[i].addEventListener("click", function () {

	photo.setAttribute("src", this.getAttribute("src"));

  });

//리뷰 답글 달기
function writeComment() {
	var reviewId = $("#A-review-id").text();
	var commentWriter = $('.seller_id').text();
	var commentText = $('#commentText').val();

	var param = {"comment" :{
			"review_id" : reviewId,
			"comment_writer_id" : commentWriter, 
			"comment_text" : commentText
    	},
    	"tag" : 'asset'
	}
	if(commentText===''){
		alert("내용을 입력해주세요")
	}else{
	  $.ajax({      
		  url : "/metahaus/reviewComment",      
		  type : "POST",  
		  data : JSON.stringify(param),
		  contentType: 'application/json',
		  success : function(){   
		        alert("작성이 완료되었습니다.");     
		        },   
		    error : function(){  
		    	alert("error");  
		        }  
		}); 
	}
}