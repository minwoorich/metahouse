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
			  	if (key != 'attach_file') {
				  	detailImageURLList.push(value);
				}
			 }
		}
	}
	function hasDuplicates(detailImageURLList) {
		  const set = new Set(detailImageURLList);
		  return set.size !== detailImageURLList.length;
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
	
	///////////////////////////////////////////
	let filename = localStorage.getItem("attach_file");
	let element = "<span>"+filename+"</span>";
	$("#attach_file").append(element);
	
	
	//등록 버튼 누른 경우
	$(".form-buttons-submit").on("click", function() {
		window.localStorage.clear();
		window.location.href = "/metahaus/asset/forms";
	});
	
	// '취소' 누른다음 취소 누른경우 -> 로컬스토리지 비워줌
    $(".cancel-modal").on("click", function(){
    	window.localStorage.clear();
    	window.location.href='/metahaus/asset/my-products?pageNo=0';
    });
	
	
});