
$(document).ready(function() {
	//구매 목록이 없을 때 띄워주는 화면
	let target = $(".asset-body-card");
	
	//구매 목록이 1개 이상일 때
	if (target.length > 0) {
		$(".asset-body-form-empty").addClass("deactivate");
	
	//구매 목록이 0개 일 때
	} else {
		$(".asset-body-form-empty").removeClass("deactivate");
	}
	
	//리뷰 모달 열기 /닫기 -> 모달을 열면서 해당 주문에 대한 주문id,프로젝트id전달
	const modal = $(".modal");
	$(".write-review").on("click", function(){
		modal.removeClass("deactivate");
		let orderId = $(this).siblings(".orderIdForReview").val();
		let assetId = $(this).siblings(".assetIdForReview").val();
		
		
//		orderId 담기
		$("#modal-orderId").val(orderId);
//		projectId 담기
		$("#modal-assetId").val(assetId); 
//		alert("orderId = " + orderId + ", projectId = " + projectId);
	});
	
	$(".close-button").on("click", function(){
		modal.addClass("deactivate");
	})
	
	//리뷰 첨부파일명 미리보기
	$('#file_attach').on('change', function(){
		var files = Array.from(this.files);
	    var fileNameList = "";
	    var paths = files.map(function(file) {
		    return file.name;
		});
	    $(".file-name").val(paths);
	});
});