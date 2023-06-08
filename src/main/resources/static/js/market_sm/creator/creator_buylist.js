
$(document).ready(function() {
	//구매 목록이 없을 때 띄워주는 화면
	let target = $(".creator-body-card");
	
	//구매 목록이 1개 이상일 때
	if (target.length > 0) {
		$(".creator-body-form-empty").addClass("deactivate");
	
	//구매 목록이 0개 일 때
	} else {
		$(".creator-body-form-empty").removeClass("deactivate");
	}
});