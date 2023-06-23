$(document).ready(function () {
    // 맨처음 시작할때 이미지 카운터
	updateImageCounters();
	
	$(".image-frame").hover(
	        function () {
	            const coverImage =
	                $("<button type='button' data-toggle='modal' data-target='#delete-img-Modal'>" +
	                    "<div>" +
	                    "<i class='fa-solid fa-trash fa-2xl' style='color: rgba(255,0,0);'></i>" +
	                    "<div/>" +
	                    "</button>").attr("class", "delete-cover");
	            $(this).append(coverImage);
	        },
	        function () {
	            $(this).find(".delete-cover").remove();
	        }
	    )


	    //사진 누르면 삭제 모달뜨고 버튼 누르면 삭제완료됨
	    var delContentImg = [];
		var delPointImg = [];
		var delStyleImg = [];
	
	    const deleteBtn = $("#delete-img-Modal").find("button");
	    //삭제할 사진을 누르면 해당 사진 객체 저장 + 모달 뜸
	    $(".image-frame").on("click", function (event) {
	        const deleteTarget = $(this);
	        //console.log($(this).attr("value"));
	        let delContentImgTag = null;
	        let delPointImgTag = null;
	        let delStyleImgTag = null;
	        
	        if($(this).attr('class') === "image-frame option1-img"){
	        	delContentImgTag = $(this).attr("value");
	        }else if($(this).attr('class') === "image-frame option2-img"){
	        	delPointImgTag = $(this).attr("value");
	        }else if($(this).attr('class') === "image-frame option3-img"){
	        	delStyleImgTag = $(this).attr("value");
	        };
	        
	        
	        //모달 버튼 누르면 삭제
	        deleteBtn.on("click", function () {
	            deleteTarget.remove();
	            if(delContentImgTag != null){
	            	delContentImg.push(delContentImgTag);
	            	delContentImgTag = null;
	            }
	            if(delPointImgTag != null){
	            	delPointImg.push(delPointImgTag);
	            	delPointImgTag = null;
	            }
	            if(delStyleImgTag != null){
	            	delStyleImg.push(delStyleImgTag);
	            	delStyleImgTag = null;
	            }
	            console.log("delContentImg: "+delContentImg);
	            console.log("delPointImg: "+delPointImg);
	            console.log("delStyleImg: "+delStyleImg);
	            //등록된 사진개수가 한도 까지 등록된경우 추가 버튼 사라짐
	            updateImageCounters();
	        });
	    });

    // 맨 처음 시작할 때 이미지 추가 버튼 상태
    function updateImageCounters() {
    	const mainImgMax = 1;
        const optionalMax = 3;
        const mainImgCnt = $(".main-img").length;
        const option1Cnt = $(".option1-img").length;
        const option2Cnt = $(".option2-img").length;
        const option3Cnt = $(".option3-img").length;
        
        $(".main-img-counter").text($(".main-img").length);
	    $(".option1-img-counter").text($(".option1-img").length);
	    $(".option2-img-counter").text($(".option2-img").length);
	    $(".option3-img-counter").text($(".option3-img").length);
        
        if (mainImgCnt === mainImgMax) {
            $(".main-img-add").addClass("full");
        } else {
            $(".main-img-add").removeClass("full");
        }
        if (option1Cnt === optionalMax) {
            $(".option1-img-add").addClass("full");
        } else {
            $(".option1-img-add").removeClass("full");
        }
        if (option2Cnt === optionalMax) {
            $(".option2-img-add").addClass("full");
        } else {
            $(".option2-img-add").removeClass("full");
        }
        if (option3Cnt === optionalMax) {
            $(".option3-img-add").addClass("full");
        } else {
            $(".option3-img-add").removeClass("full");
        }
    }
    
    let thumbnail = null
    $("#portfolio_img_insert").on("input", function() {
    	let file = $(this)[0].files[0];
    	console.log("main_img의 id : "+$(this).attr("id"));
    	thumbnail = file;
    	// 업로드할 이미지의 경로(미리보기용)
    	let fileURL = URL.createObjectURL(file);
    	console.log("thumbnail : ",thumbnail);
    	// 미리보기할 이미지를 담은 요소
    	let element = $("<div class='image-frame main-img'>" +
    					"<img src='" + fileURL + "' alt='대표 사진'>" +
        				"</div>");
    	$("#main_img_parent").append(element);
    	// 이미지 카운터 업데이트
    	updateImageCounters();

    	element.on("click", function() {
    		// 모달 버튼 누르면 삭제
    		const deleteBtn = $("#delete-img-Modal").find("button");
    		const deleteTarget = $(this);

    		deleteBtn.on("click", function() {
    			deleteTarget.remove(); //미리보기 요소 삭제
    			$("#thumbnail").val(null); //input태그의 value null로 초기화시키기
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
    });
    
    var ContentfileMap = new Map();
    //상세사진의 file들 담는 리스트
    var ContentfileList = [];
    //상세사진의 URL(filepath)들 담는 리스트
    var ContentfileURLList = [];
    function CotentupdateLists() {
    	// 리스트 초기화
    	ContentfileURLList = [];
    	ContentfileList = [];
    	
    	// Map 객체 순회
    	ContentfileMap.forEach(function(file, fileURL) {
    		ContentfileURLList.push(fileURL);
    		ContentfileList.push(file);
    	});
    	// 리스트 출력
    	console.log(ContentfileURLList);    
    	console.log(ContentfileList);  
    }
    
    $("#portfolio_img_content_insert").on("input", function() {
    	let file = $(this)[0].files[0];
    	console.log("content_img의 id : "+$(this).attr("id"));
        // 업로드할 이미지의 경로(미리보기용)
        let fileURL = URL.createObjectURL(file);
        //Map에 데이터 추가
        ContentfileMap.set(fileURL, file);
        //리스트들 업뎃
        CotentupdateLists();
        // 미리보기할 이미지를 담은 요소
        let element = $("<div class='image-frame option1-img'>" +
          "<img src='" + fileURL + "' alt='대표 사진'>" +
          "</div>");
        $("#content_img_parent").append(element);
        // 이미지 카운터 업데이트
        updateImageCounters();

        element.on("click", function() {
        	// 모달 버튼 누르면 삭제
        	const deleteBtn = $("#delete-img-Modal").find("button");
        	// 삭제할 요소
        	const deleteTarget = $(this);
        	// 삭제할 요소의 imgURL
        	const deleteTargetURL = deleteTarget.find("img").attr("src");
         
        	deleteBtn.on("click", function() {
        		deleteTarget.remove();//요소 삭제
        		fileMap.delete(deleteTargetURL);//fileMap에서 file삭제
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
    });
        
        
    var PointfileMap = new Map();
    //상세사진의 file들 담는 리스트
    var PointfileList = [];
    //상세사진의 URL(filepath)들 담는 리스트
    var PointfileURLList = [];
    function PointupdateLists() {
    	// 리스트 초기화
    	PointfileURLList = [];
    	PointfileList = [];
    	
    	// Map 객체 순회
    	PointfileMap.forEach(function(file, fileURL) {
    		PointfileURLList.push(fileURL);
    		PointfileList.push(file);
    	});
    	// 리스트 출력
    	console.log(PointfileURLList);    
    	console.log(PointfileList);  
    }
    
    $("#portfolio_img_point_insert").on("input", function() {
    	let file = $(this)[0].files[0];
    	console.log("point_img의 id : "+$(this).attr("id"));
        // 업로드할 이미지의 경로(미리보기용)
        let fileURL = URL.createObjectURL(file);
        //Map에 데이터 추가
        PointfileMap.set(fileURL,file);
        //리스트들 업뎃
        PointupdateLists();
        
        // 미리보기할 이미지를 담은 요소
        let element = $("<div class='image-frame option2-img'>" +
          "<img src='" + fileURL + "' alt='대표 사진'>" +
          "</div>");
        $("#point_img_parent").append(element);
        // 이미지 카운터 업데이트
        updateImageCounters();

        element.on("click", function() {
        	// 모달 버튼 누르면 삭제
        	const deleteBtn = $("#delete-img-Modal").find("button");
        	// 삭제할 요소
        	const deleteTarget = $(this);
        	// 삭제할 요소의 imgURL
        	const deleteTargetURL = deleteTarget.find("img").attr("src");
         
        	deleteBtn.on("click", function() {
        		deleteTarget.remove();//요소 삭제
        		fileMap.delete(deleteTargetURL);//fileMap에서 file삭제
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
    });
        
        
    var StylefileMap = new Map();
    //상세사진의 file들 담는 리스트
    var StylefileList = [];
    //상세사진의 URL(filepath)들 담는 리스트
    var StylefileURLList = [];
    function StyleupdateLists() {
    	// 리스트 초기화
    	StylefileURLList = [];
    	StylefileList = [];
    	
    	// Map 객체 순회
    	StylefileMap.forEach(function(file, fileURL) {
    		StylefileURLList.push(fileURL);
    		StylefileList.push(file);
    	});
    	// 리스트 출력
    	console.log(StylefileURLList);    
    	console.log(StylefileList);  
    }
    
    $("#portfolio_img_style_insert").on("input", function() {
    	let file = $(this)[0].files[0];
    	console.log("style_img의 id : "+$(this).attr("id"));
        // 업로드할 이미지의 경로(미리보기용)
        let fileURL = URL.createObjectURL(file);
        //Map에 데이터 추가
        StylefileMap.set(fileURL,file);
        //리스트들 업뎃
        StyleupdateLists();
        
        // 미리보기할 이미지를 담은 요소
        let element = $("<div class='image-frame option3-img'>" +
          "<img src='" + fileURL + "' alt='대표 사진'>" +
          "</div>");
        $("#style_img_parent").append(element);
        // 이미지 카운터 업데이트
        updateImageCounters();

        element.on("click", function() {
        	// 모달 버튼 누르면 삭제
        	const deleteBtn = $("#delete-img-Modal").find("button");
        	// 삭제할 요소
        	const deleteTarget = $(this);
        	// 삭제할 요소의 imgURL
        	const deleteTargetURL = deleteTarget.find("img").attr("src");
         
        	deleteBtn.on("click", function() {
        		deleteTarget.remove();//요소 삭제
        		fileMap.delete(deleteTargetURL);//fileMap에서 file삭제
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
    });
    
    
    
    
    
    $("#protfolio_content_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text1-counter");
        const $cousin = $target.parent().siblings().children().children();
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        // console.log($length+ "맥스 : "+$maxLength);
        if ($length == $maxLength) {
            $counter.addClass("max-length");
        } else {
            $counter.removeClass("max-length");
        }
    });
    
    $("#protfolio_content_point_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text2-counter");
        const $cousin = $target.parent().siblings().children().children();
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        // console.log($length+ "맥스 : "+$maxLength);
        if ($length == $maxLength) {
            $counter.addClass("max-length");
        } else {
            $counter.removeClass("max-length");
        }
    });
    
    $("#protfolio_content_style_textarea").on("input", function (event) {
        const $target = $(event.target); //이벤트 발생한 요소
        const $counter = $target.parent().siblings().find(".text3-counter");
        const $cousin = $target.parent().siblings().children().children();
        const $maxLength = parseInt($target.attr("maxlength")); //최대입력수

        const $length = $(this).val().length;
        $counter.text($length);
        // console.log($length+ "맥스 : "+$maxLength);
        if ($length == $maxLength) {
            $counter.addClass("max-length");
        } else {
            $counter.removeClass("max-length");
        }
        
        if ($length == $maxLength) {
            $counter.css("color","red");
        } else {
            $counter.css("color","var(--gray)");
        }
        
    });
    
    var AttachfileList = [];
    $("#portfolio_content").on("input", function() {
    	let file = $(this)[0].files[0];
    	
    	AttachfileList.push(file);
    	console.log(AttachfileList);
    });
    
    
    $("#submit").on("click", function(){
    	let myformdata = new FormData();
		let userId = $("#userId").val();
		let category1 = $("#category1").val();
		let category2 = $("#category2").val();
		let title = $("#title").val();
		let startDay = $("#startDay").val();
		let endDay = $("#endDay").val();
		let nop = $("#nop").val();
		let multipartMainImg = thumbnail;
		
		let portfolioPjContentImg = ContentfileList;
		let portfolioPjContent = $("#protfolio_content_textarea").val();
		
		let portfolioPjPointImg = PointfileList;
		let portfolioPjPoint = $("#protfolio_content_point_textarea").val();
		
		let portfolioPjStyleImg = StylefileList;
		let portfolioPjStyle = $("#protfolio_content_style_textarea").val();
		
		let portfolioAttachFile = AttachfileList;
		
		console.log(myformdata+","+userId+","+category1+","+category2+","+title+","+startDay+","+endDay+","+nop+","+multipartMainImg+","+
				portfolioPjContentImg+","+portfolioPjContent+","+portfolioPjPointImg+","+portfolioPjPoint+","+portfolioPjStyleImg+","+portfolioPjStyle+","+portfolioAttachFile);
		
		myformdata.append("portfolio_id", null);
		myformdata.append("user_id", userId);
		myformdata.append("category1", category1);
		myformdata.append("category2", category2);
		myformdata.append("portfolio_title", title);
		myformdata.append("portfolio_pj_start_day", startDay);
		myformdata.append("portfolio_pj_end_day", endDay);
		myformdata.append("nop", nop);
		
		myformdata.append("portfolio_pj_content", portfolioPjContent);
		myformdata.append("portfolio_pj_point", portfolioPjPoint);
		myformdata.append("portfolio_pj_style", portfolioPjStyle);
		
		
		if(multipartMainImg != null){
			myformdata.append("multipartMainImg", multipartMainImg);
		}
		
		//리스트로 보낼때 for문으로 동작시켜줘야한다.
		if(portfolioPjContentImg.length != 0){
			for (let i = 0; i < portfolioPjContentImg.length; i++) {
				myformdata.append("portfolioPjContentImg", portfolioPjContentImg[i]);
			}
		}
		
		if(portfolioPjPointImg.length != 0){
			for (let i = 0; i < portfolioPjPointImg.length; i++) {
				myformdata.append("portfolioPjPointImg", portfolioPjPointImg[i]);
			}
		}
		
		if(portfolioPjStyleImg.length != 0){
			for (let i = 0; i < portfolioPjStyleImg.length; i++) {
				myformdata.append("portfolioPjStyleImg", portfolioPjStyleImg[i]);
			}
		}
		
		if(portfolioAttachFile.length != 0){
			for (let i = 0; i < portfolioAttachFile.length; i++) {
				myformdata.append("portfolioAttachFile", portfolioAttachFile[i]);
			}
		}
		
		
		//기존 이미지 중 삭제된 부분 넣음
		myformdata.append("delContentImg", delContentImg);
		myformdata.append("delPointImg", delPointImg);
		myformdata.append("delStyleImg", delStyleImg);
		
		//기존 이미지를 삭제하지 않고 추가한다면 추가될 위치에 대한 값
		let contentSize = $("#content-size").val();
		let pointSize = $("point-size").val();
		let styleSize = $("style-size").val();		
		
		myformdata.append("contentSize", contentSize);
		myformdata.append("pointSize", pointSize);
		myformdata.append("styleSize", styleSize);
		
		$.ajax({
			url:"/metahaus/mypage/update_portfolio",
			type:"post",
			processData:false,
			contentType:false,
			cache:false,
			data:myformdata,
			success:function(nextPageURL){
				location.href = nextPageURL;
				
			},
			error:function(request,status,error){	
		        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
		
    });
    
});