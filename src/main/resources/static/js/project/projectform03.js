$(document).ready(function(){
	let thumbnailURL = null;
	let detailImageURLList = [];
	//로컬스토리지에서 이미지URL 추출하는 함수
	function extractLocalStorageValue(){
		for (let i = 0; i < localStorage.length; i++) {
			  let key = localStorage.key(i);
			  let value = localStorage.getItem(key);
			  if(key === "thumbnailURL"){
				  thumbnailURL = value;
			  }else{
				  detailImageURLList.push(value);
			  }
		}
	}
	
	extractLocalStorageValue();//로컬스토리지에서 이미지URL 추출하는 함수
	
	// 썸네일 이미지 URL 설정해줌
	$("#thumbnail-img").attr("src",thumbnailURL);
	
	//상세 이미지 URL 설정해줌
	let count=1;
	for(let imageUrl of detailImageURLList){
		let detailImageElement = 
			"<div class='image-frame'>"+
				"<img id='detail-img"+count+"' src=''>"+
			"</div>"
		$(".form-item-images-wrapper").append(detailImageElement);
		$("#detail-img"+count).attr("src",imageUrl);
		count++;
	}
	///////////////////////////////////
	
	//등록 버튼 누른경우
	$(".form-buttons-submit").on("click",function(){
		window.localStorage.clear();
		window.location.href="/metahaus/project/forms";
	});
	
	
	
					
	
	
	
});