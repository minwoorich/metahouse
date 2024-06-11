let editor;   // CK에디터 인스턴스 담을 객체, 전역 선언(projectform01.html에서도 쓰려고)

$(document).ready(function () {
 
    // 사용자가 입력한 글자수 카운팅
    $(".form-item-description").on("input", function (event) {
        const $target = $(event.target); // 이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".counter");
        
        const $maxLength = parseInt($target.attr("maxlength")); // 최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
    });
    
 // 맨 처음 시작할 때 이미지 카운터
    function updateImageCounters() {
    	const thumbnailMax = 1;
        const optionalMax = 6;
        const thumbnailCnt = $(".thumbnail-img").length;
        const optionalCnt = $(".optional-img").length;
        $(".thumbnail-img-counter").text(thumbnailCnt);
        $(".optional-img-counter").text(optionalCnt);

        // 이미지 추가 버튼 상태
        if (thumbnailCnt === thumbnailMax) {
            $(".thumbnail-add").addClass("full");
        } else {
            $(".thumbnail-add").removeClass("full");
        }

        if (optionalCnt === optionalMax) {
            $(".optional-add").addClass("full");
        } else {
            $(".optional-add").removeClass("full");
        }
    }
    
    
 // 이미지 추가 버튼 클릭 시 미리보기 생성 - 대표이미지
    let thumbnailElement = null; // 컨트롤러로 전송할때 쓸 변수
    $("#thumbnail").on("input", function() {
      let file = $(this)[0].files[0];
      let reader = new FileReader();
     
      thumbnailElement = file;
      // 업로드할 이미지의 경로(미리보기용)
      reader.onload = function(e) {
    	  const fileURL = e.target.result;
    	  // 미리보기할 이미지를 담은 요소
    	  let element = $("<div class='image-frame thumbnail-img'>" +
		          "<img src='" + fileURL + "' alt='대표 사진'>" +
		          "</div>");
    	  $("#thumbnail-form").append(element);
	        // 이미지 카운터 업데이트
	        updateImageCounters();
	  	    // 이미지 파일을 로컬 스토리지에 저장
	  	    localStorage.setItem('thumbnailURL', fileURL);
	  	    
	  	  element.on("click", function() {
	          // 모달 버튼 누르면 삭제
	          const deleteBtn = $("#delete-img-Modal").find("button");
	          const deleteTarget = $(this);

	          deleteBtn.on("click", function() {
	            deleteTarget.remove(); // 미리보기 요소 삭제
	            $("#thumbnail").val(null); // input태그의 value null로 초기화시키기
	            // 이미지 카운터 업데이트
	            updateImageCounters();
	          });
	        });

	        element.on("mouseenter", function() {
	          const coverImage =
	            $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
	              "<div>" +
	              "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0,0.5);'></i>" +
	              "<div/>" +
	              "</button>").attr("class", "delete-cover");
	          $(this).append(coverImage);
	        });

	        element.on("mouseleave", function() {
	          $(this).find(".delete-cover").remove();
	        });
  	  }
  	  // 파일을 읽고 Base64로 변환
  	  reader.readAsDataURL(file);
      
     
    });
    
    $("#attach_file").on("input", function() {
    	let file = $(this)[0].files[0];
    	let filename = file.name;
    	localStorage.setItem('attach_file', filename);
    	
    	
    });
    // //////////////////////////////////////////////////////////////////////////////////
    let fileMap = new Map();
    let fileList = [];// 상세사진의 file들 담는 리스트
    let fileURLList = [];// 상세사진의 URL(filepath)들 담는 리스트
    function updateLists() {
    	  // 리스트 초기화
    	fileURLList = [];
    	fileList = [];

    	  // Map 객체 순회
    	fileMap.forEach(function(file, fileURL) {
    		fileURLList.push(fileURL);
    	    fileList.push(file);
    	  });
    	// 리스트 출력
    	  console.log(fileURLList);    
    	  console.log(fileList);  
    }
    $("#optional").on("input", function() {
      let file = $(this)[0].files[0];
      // 업로드할 이미지의 경로(미리보기용)
      let reader = new FileReader();
      
      reader.onload = function(e){
    	  const fileURL = e.target.result;
    	  
    	// 미리보기할 이미지를 담은 요소
          let element = $("<div class='image-frame optional-img'>" +
            "<img src='" + fileURL + "' alt='대표 사진'>" +
            "</div>");
          $(".form-item-images-wrapper").append(element);
          // 이미지 카운터 업데이트
          updateImageCounters();
          // 이미지 파일을 로컬 스토리지에 저장
	  	  localStorage.setItem(fileURL, fileURL);
	  	  
	  	 // Map에 데이터 추가
	      fileMap.set(fileURL,file);
	      // 리스트들 업뎃
	      updateLists();
	      
	      element.on("click", function() {
	          // 모달 버튼 누르면 삭제
	          const deleteBtn = $("#delete-img-Modal").find("button");
	          // 삭제할 요소
	          const deleteTarget = $(this);
	          // 삭제할 요소의 imgURL
	          const deleteTargetURL = deleteTarget.find("img").attr("src");
	         
	          deleteBtn.on("click", function() {
	            deleteTarget.remove();// 요소 삭제
	            fileMap.delete(deleteTargetURL);// fileMap에서 file삭제
	            localStorage.removeItem(deleteTargetURL);// 파일 URL에 해당하는 로컬 스토리지 항목 제거
	            
	            // 리스트들 업뎃
	            updateLists();
	            // 이미지 카운터 업데이트
	            updateImageCounters();
	          });
	        });

	        element.on("mouseenter", function() {
	          const coverImage =
	            $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
	              "<div>" +
	              "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0,0.5);'></i>" +
	              "<div/>" +
	              "</button>").attr("class", "delete-cover");
	          $(this).append(coverImage);
	        });

	        element.on("mouseleave", function() {
	          $(this).find(".delete-cover").remove();
	        });
	      };
    	  reader.readAsDataURL(file);
    });
      
     
      
      

     
	
    // CK에디터
    
    ClassicEditor
    .create( document.querySelector( '#form-editor' ), {
      toolbar: ['Undo', 'Redo', 'heading', '|', 'bold', 'italic']
    } )
    .then(instance=> {
      editor=instance;
      var defaultText =
        " <strong>[상품 등록 시 유의사항]</strong><br/><br/>"+
        "1. 규정 및 법률 준수 : <br/>"+
        "- 지적 재산권 침해나 불법 복제를 방지하기 위한 관련 규정을 숙지하고 준수해야 합니다.<br/><br/>"+
        "2. 라이선싱 및 사용권 : <br/>"+
        "- 에셋을 판매할 때는 해당 에셋의 라이선싱 및 사용권을 명확하게 정의해야 합니다.<br/>"+
        "- 어떤 종류의 라이선스를 제공하는지, 어떤 용도로 사용할 수 있는지, 에셋에 관련된 제한사항이 있는지 등을 명시해야 합니다.<br/><br/>"+
        "3. 에셋 설명 및 정보 : <br/>"+
        "- 판매할 에셋에 대한 명확하고 자세한 설명을 제공해야 합니다.<br/>"+
        "- 에셋의 특징, 기능, 사용 방법 등에 대한 정보를 포함시켜 구매자들이 에셋을 이해하고 활용할 수 있도록 해야 합니다.<br/><br/>"+
        "4. 가격 설정 : <br/>"+
        "- 에셋을 판매할 때 적절한 가격을 설정해야 합니다.<br/>"+
        "- 시장 조사를 통해 유사한 에셋의 가격 범위를 파악하고, 에셋의 품질과 가치를 고려하여 가격을 책정해야 합니다.<br/>"+
        "- 과도한 가격이나 지나치게 저렴한 가격은 구매자들에게 좋지 않은 인상을 줄 수 있습니다.<br/><br/>"+
        "5. 에셋의 품질 관리 : <br/>"+
        "- 판매하는 에셋은 최상의 품질을 유지해야 합니다.<br/>"+
        "- 버그나 결함이 있는 경우, 이를 수정하고 업데이트하여 구매자들에게 최적의 경험을 제공해야 합니다.<br/>"+
        "- 에셋의 품질 관리와 지속적인 업데이트는 구매자들의 신뢰를 유지하는 데 중요합니다.<br/><br/>"+
        "6. 지원 및 고객 서비스 : <br/>"+
        "- 판매한 에셋과 관련된 지원과 고객 서비스를 제공해야 합니다.<br/>"+
        "- 구매자들이 문의나 문제를 해결할 수 있는 경로를 제공하고, 에셋 사용에 관한 질문이나 요청에 대응해야 합니다.<br/><br/><br/>";
        //"<strong>[구매 시 유의사항]</strong><br/><br/>"+
        //"만족스러운 결과물을 위해, 아래 내용을 미리 준비해 주세요.<br/>"+
        //"- 취향<br/>"+
        //"- 컨셉·분위기<br/>"+
        //"- 원하는 색상<br/>"+
        //"- 레퍼런스(참고할 만한 디자인)";
      var contents = defaultText;
      // 에디터 인스턴스 생성 후 setData 메서드를 사용하여 초기값 설정
      editor.setData(contents);
      // 글자수 카운트
      var charCountContainer = $('#editor-counter');

      // 초기 글자 수 업데이트
      var initialContent = editor.getData();
      var initialCharCount = countCharacters(initialContent);
      charCountContainer.text(initialCharCount);

      // 에디터 내용 변경 시 글자 수 업데이트
      editor.model.document.on( 'change:data', function() {
        var MAXLENGTH = 1500;
        var content = editor.getData();
        var charCount = countCharacters(content);
        charCountContainer.text(charCount);
      });

      // 문자열에서 HTML 태그를 제외한 글자 수를 세는 함수
      function countCharacters(text) {
        var strippedText = text.replace(/<\/?[^>]+(>|$)/g, '');
        return strippedText.length;
      }
    })
    .catch(function(error) {
      console.error(error);
    });
    
    
    // "다음" 버튼 누른경우 -
    $(".form-buttons-next").on("click",function(event){
    	event.preventDefault();
    	let isValid = true;
    	const alertMsg = $("<div>항목을 채워주세요</div>").attr("class", "alert-msg");
    	
    	// 필수사항 폼에 아무것도 입력 하지 않은경우
    	$('textarea[required]').each(function() {
			  if ($(this).val().trim() === '') {
				  $(this).parent().parent().addClass('alert-style');
				  isValid=false;
			  }
    	});
    	if($(".thumbnail-img").length == 0){
    		$(".thumbnail-form").addClass("alert-style");
    	}
    	if(!isValid){// 필수 사항 미 완성 -> 스크롤이 맨 위로 올라감
    		$('html, body').animate({ scrollTop: 0 }, "0.5s");
    		
    	}else{// 전부 채워넣은경우 -> 데이터 전송
    		let myformdata = new FormData($("#form")[0]);
    		// 보낼 데이터들
    		let user_id = $("#user_id").val();// [추후수정] 수정될 수 있음
    		let title = $("#title").val();
    		let category1 = $("#category1").val();
    		let category2_as = $("#category2_as").val();
    		let description = editor.getData();
    		let price = $("#price").val();
    		let thumbnail = thumbnailElement;
    		let detailImages = fileList;
    		let attach_file = $("#attach_file").val();
    		
    		console.log("user_id = "+user_id+
    				", title = "+title+
    				", category1 = " + category1+
    				",category2_as="+category2_as+
    				", description = "+description+
    				",price="+price+
    				", thumbnail = " + thumbnail+
    				", detailImages = " + detailImages+
    				", attach_file = " + attach_file);
    		
    		
    		myformdata.append("description",description);
    		myformdata.append("thumbnail",thumbnail);
    		
    		for(let image of detailImages){
                myformdata.append("detailImages",image);
            }
    		
    		myformdata.append("attach_file",attach_file);
    		
    		$.ajax({
				url:"/metahaus/asset/forms-ajax",
				type:"post",
				processData:false,
				contentType:false,
				cache:false,
				data:myformdata,
				success:function(nextPageURL){
					//let img = detailImages.length
					//alert(img);
					//nextPageURL = /metahaus/asset/forms/preview
					window.location.href = nextPageURL;
				},
				error:function(request,status,error){
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
			});
    	}
    	
  	});
    
    
    // '취소' 누른다음 취소 누른경우 -> 로컬스토리지 비워줌
    $(".cancel-modal").on("click", function(){
    	window.localStorage.clear();
    	window.location.href='/metahaus/asset/my-products?pageNo=0';
    });
    
    // textarea에 값을 입력하면 alert-style 사라짐
    $("textarea").on("input",function(){
    	if($(this).val()!==""){
    		$(this).parent().parent().removeClass("alert-style");
    	}
    });
    
    // 사진 추가하면 alsert-style 사라짐
    if($(".thumbnail-img").length !== 0){
		$(".thumbnail-form").removeClass("alert-style");
	}
	
});
