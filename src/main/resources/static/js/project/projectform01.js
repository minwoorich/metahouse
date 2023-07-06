let editor;   // CK에디터 인스턴스 담을 객체, 전역 선언(projectform01.html에서도 쓰려고)
let thumbnailFile;;
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
     // 컨트롤러로 전송할때 쓸 변수
    $("#thumbnail").on("input", function() {
      let file = $(this)[0].files[0];
      let reader = new FileReader();
      thumbnailFile = file;
      
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
      toolbar: ['Undo', 'Redo', 'heading','JustifyLeft','JustifyCenter','JustifyRight', '|', 'bold', 'italic']
    } )
    .then(instance=> {
      editor=instance;
      var defaultText =
        " <strong>[서비스 소개]</strong><br/><br/>"+
        "- 제작물에 대한 기본 저작권은 디자이너에게 귀속됩니다. 협의 후 저작권료 지급 시 귀속 가능합니다.<br/>"+
        "- 디자이너와 협의 한 범위 내에서 상업적 이용이 가능합니다.<br/>"+
        "- 제작된 작업물은 포트폴리오로 사용될 수 있습니다. 원치 않으실 경우 미리 말씀해 주세요.<br/>"+
        "- 원저작물의 2차 저작(수정, 변형, 가공) 시, 디자이너의 허가가 필요합니다.<br/><br/><br/>"+
        "<strong>[구매 시 유의사항]</strong><br/><br/>"+
        "만족스러운 결과물을 위해, 아래 내용을 미리 준비해 주세요.<br/>"+
        "- 취향<br/>"+
        "- 컨셉·분위기<br/>"+
        "- 원하는 색상<br/>"+
        "- 레퍼런스(참고할 만한 디자인)";
      //var contents = defaultText;
      // 에디터 인스턴스 생성 후 setData 메서드를 사용하여 초기값 설정
      editor.setData(defaultText);
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
    		let userId = $("#creator_id").val();
    		let title = $("#title").val();
    		let category1 = $("#category1").val();
    		let category2_pj = $("#category2_pj").val();
    		let description = editor.getData();
    		let thumbnail = thumbnailFile;
    		let detailImages = fileList;
    		
    		console.log("userId = "+userId+
    				", title = "+title+
    				", category1 = " + category1+
    				",category2_pj="+category2_pj+
    				", description = "+description+
    				", thumbnail = " + thumbnail+
    				", detailImages = " + detailImages);
    		
    		myformdata.append("description",description);
    		myformdata.append("thumbnail",thumbnail);
    		for(let image of detailImages){
    			myformdata.append("detailImages",image);
    			console.log("image : " + image);
    		}
    		
    		$.ajax({
				url:"/metahaus/project/forms-ajax",
				type:"post",
				processData:false,
				contentType:false,
				cache:false,
				data:myformdata,
				success:function(nextPageURL){
//					alert(description);
					//nextPageURL = /metahaus/project/forms/packages
					window.location.href = nextPageURL;
				},
				error:function(request,status,error){
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
			});
    	}
    	
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
