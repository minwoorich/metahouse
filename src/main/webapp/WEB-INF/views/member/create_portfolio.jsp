<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/mh/css/buttons.css">
	<link rel="stylesheet" href="/mh/css/style.css">
	<link rel="stylesheet" href="/mh/css/member/create_protfolio.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/38.0.1/classic/ckeditor.js"></script>
</head>
	<form>
		<div class="creat_portfolio_title">포트폴리오 생성</div>
		
		<div class="creat_portfolio_subtitle">포트폴리오 이미지 입력</div>
		<div class="create_portfolio_input_img">
			<label class="img_insert_lable" for="portfolio_img_insert">
				<div class="create_portfolio_img">
					사진추가
				</div>
			</label> 
			<input type="file" id="portfolio_img_insert">
		</div>
		<div class="creat_portfolio_subtitle">포트폴리오 내용 입력</div>
		<div class="create_portfolio_input_text">
			<textarea class="create_portfolio_text_area" id="protfolio_content_textarea"></textarea>
		</div>
		<div class="create_portfolio_input_content">
			<label for="portfolio_content" class="create_portfolio_input">첨부파일</label>
			<input type="file" class="portfolio_content"
					id="portfolio_content" placeholder="파일선택">
		</div>
		<div class="create_portfolio_submit">
			<button class="button--small">작성완료</button>
		</div>
	</form>
	<script>
		ClassicEditor
			.create( document.querySelector( '#protfolio_content_textarea' ), {
				toolbar: ['Undo', 'Redo', 'heading', '|', 'bold', 'italic']
			} )
			.catch( error => {
				console.error( error );
			});
	</script>
</body>
</html>