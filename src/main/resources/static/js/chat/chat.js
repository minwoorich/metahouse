$(document).ready(function(){
	// 세션 로그인 객체 저장 용도
	let loginUser = null;
	// chatroom 목록 elements로부터 채팅방 id 호출해 저장할 용도 
	let chatroomId = null;
	// 웹 소켓
	let websocket = null;
	// Controller에 전송할 JSON
	let chatMsgData = {};
	// 채팅 스크롤 관련
	let scrollChatBody = $(".chat-body__chat-section");
	// 메시지 첨부 파일 관련 List
	let fileList = [];
	let filenameList = [];
	// JSON 텍스트 메시지 저장 용도
	let resMsg = "";
	// 메시지 출력 Element 저장 용도
	let ElementItem = "";
	// 파일 저장 용도
	let fileUrl = "";
	// 파일 개수 count 용도
	let fileCount = 0;
	// KST 설정
	const KR_TIME_DIFF = 9 * 60 * 60 * 1000;
	
	// 채팅방 목록 onClick eventListener
	$(".chatroom-card").on("click", async function(){
		// 숨김 처리되었던 채팅방 요소 보이기
		$(".chat-main").css("display", "flex");
		
		chatroomId = $(this).attr("id").replace("chatroom", "");
		
		loginUser = $("#loginUser").val();
		
		// 채팅방의 채팅 불러오기
		try {
			const jsonData = await $.ajax({
			  url: "/metahaus/chat/load/chat",
			  type: "get",
			  data: {
				chatroomId: chatroomId,
				loginUser: loginUser,
			  },
			  dataType: "json",
			});
		
			let chatMsg = jsonData.chatMsg;
			let targetProfile = jsonData.targetProfile;
		
			// 채팅방 이름 설정
			$(".chat-header").children("h1").text(targetProfile.user_name);
		
			// 채팅 요소 작성
			const chatElements = await Promise.all(
			  chatMsg.map(async (msg) => await createChatElement(msg, loginUser))
			);
		
			// 테스트 채팅 요소 삭제
			$(".chat-body__chat-section").children().remove();
		
			// 정렬된 채팅 요소 추가
			chatElements.forEach((ele) => $(".chat-body__chat-section").append(ele));
		
			// 프로필 요소 작성
			const profile = createProfileElement(targetProfile);
		
			// 기존 프로필 요소 삭제
			$(".chat_body_profile-img_div").remove();
		
			// 프로필 요소 추가
			$(".chat-body__profile-section").append(profile);
		
			// 채팅 구현
			if (!websocket || websocket.readyState !== websocket.OPEN) {
			  websocket = new WebSocket("ws://" + location.host + "/metahaus/chat");
			  /*console.log("web 소켓 생성 : " + websocket);*/
			} else {
			  /*console.log("open중인 web 소켓이 이미 존재합니다.");*/
			}
		
			// Server에 메시지 존재할 경우 수신
			websocket.onmessage = function(msg){
				
				// String Type의 메시지를 수신한 경우 (JSON String)
				if(typeof msg.data === "string"){
					resMsg = JSON.parse(msg.data);
					
					let msgCss = "";
					let msgBlockCss = "";
					
					if(resMsg.writer_id === loginUser){
						// 내 채팅
						msgBlockCss = "class='chat-block chat-block--send'";
						msgCss = "class='chat-block__message chat-block__message--send'";
					}else{
						// 상대방 채팅
						msgBlockCss = "class='chat-block chat-block--reception'";
						msgCss = "class='chat-block__message chat-block__message--reception'";
					}
					
					// 일반 텍스트 메시지인 경우
					if(resMsg.message_type === "Text"){
						// 메시지 객체에 저장된 실제 데이터 꺼내기
						ElementItem += '<div ' + msgBlockCss + '><div ' + msgCss + '><div class="chat-block__message-text">'
									+ resMsg.message_content
									+ '</div><div class="chat-block__message-files"></div></div>'
									+ '<div class="chat-block__timestamp"><div class="chat-block__timestamp-date">'
									+ resMsg.write_time.substr(2, 8).replaceAll("-", ".")
									+ '</div><div class="chat-block__timestamp-time">'
									+ resMsg.write_time.substr(11, 5)
									+ '</div></div></div>';
						
						$(".chat-body__chat-section").append(ElementItem);
						ElementItem = "";
					
					// 파일이 첨부된 메시지인 경우
					}else{	
						ElementItem += '<div ' + msgBlockCss + '><div ' + msgCss + '><div class="chat-block__message-text">'
									+ resMsg.message_content
									+ '</div>'
									+ '<div class="chat-block__message-files">';
						
						fileCount = 0; // 파일 개수 count
					}
				// Binary Type 의 메시지를 수신한 경우 1 (File 전송 - 해당 메시지의 마지막 파일을 수신한 경우)
				}else if (msg.data instanceof Blob && (fileCount+1) == resMsg.filenameList.length){
					fileUrl = URL.createObjectURL(new Blob([msg.data]));
					ElementItem += '<div class="message-file">' 
								+ '<img class="file_img" src="'+ fileUrl + '"/>' 
								+ '<div>'+resMsg.filenameList[fileCount]+'</div>' 
								+ '</div></div></div>' 
								+ '<div class="chat-block__timestamp"><div class="chat-block__timestamp-date">'
								+ resMsg.write_time.substr(2, 8).replaceAll("-", ".")
								+ '</div><div class="chat-block__timestamp-time">'
								+ resMsg.write_time.substr(11, 5)
								+ '</div></div>';
					$(".chat-body__chat-section").append(ElementItem);
					ElementItem = "";
				
				// Binary Type 의 메시지를 수신한 경우 2 (File 전송)
				}else{
					fileUrl = URL.createObjectURL(new Blob([msg.data]));
					ElementItem += '<div class="message-file">'
								+ '<img class="file_img" src="' + fileUrl + '"/>'
								+ '<div>' + resMsg.filenameList[fileCount]+'</div>'
								+ '</div>';
					fileCount++;
				}
				// 최근 메시지 변경
				chatroom = "chatroom" + chatroomId;
				$("#"+chatroom).find(".last-chat").text(resMsg.message_content);
				$("#"+chatroom).find(".last-date").text(resMsg.write_time.substr(2, 8));
				
				/* 채팅 스크롤 항상 아래로 */
				scrollChatBody.scrollTop(scrollChatBody[0].scrollHeight);
			}
			/* 채팅 스크롤 항상 아래로 */
			scrollChatBody.scrollTop(scrollChatBody[0].scrollHeight);
		  } catch (error) {
			/*console.error("Error during AJAX request:", error);*/
			alert("문제가 발생했습니다. 관리자에게 문의하세요.");
		  }
	}) /* End of Chatroom Click Event Fn */
	
	// 채팅 전송 이벤트 (버튼 클릭)
	$(".send-button").on("click", function(){
		fileList.length > 0 ? sendMsgWithFiles() : sendMsg();
	})
	
	// 채팅 전송 이벤트 (엔터 입력)
	$(".chat-footer-row01").on("keyup", function(){
		if(event.keyCode == 13){ // 엔터 입력시	
			fileList.length > 0 ? sendMsgWithFiles() : sendMsg();
		}
	})
	
	// 웹소켓 종료 이벤트 (미구현)
	$("#btnclose").on("click", function(){
		// 웹 소켓 종료
		websocket.close();
	})
	
	// 첨부 파일 업로드 이벤트
	$("#file_attach").on("change", function(){
		console.log("첨부파일 업로드 클릭!");
		let file = $(this)[0].files[0];
		let filename = getFileNameFromBlob(file);
		fileList.push(file);
		filenameList.push(filename);
		console.log("fileList : " + fileList);
		console.log("filenameList : " + filenameList);
		
	})
	
	/* 파일 첨부 메시지의 동적 태그에 download function 이벤트 추가 */
	$(document).on('click', '.file_img', function(){
		console.log("파일을 다운로드합니다.");
		console.log($(this));
		const aTag = document.createElement('a');
		document.body.appendChild(aTag);
		aTag.download = $(this).next("div").text();
		aTag.href = $(this).attr("src");
		aTag.click();
	})
	
	/* 일반 메시지 전송 메소드 */
	function sendMsg(){
		console.log("일반 메시지");
		let msg = $(".chat-footer-row01").val();
		 
		// 일반 메시지 JSON 정의
		chatMsgData.message_type = "Text";
		chatMsgData.writer_id = loginUser;
		chatMsgData.chatroom_id = chatroomId;
		chatMsgData.message_content = msg;
		chatMsgData.write_time = new Date(new Date().getTime() + KR_TIME_DIFF);
		
		let sendMsg = JSON.stringify(chatMsgData); // json 문자열로 변환
		console.log("sendMsg : " + sendMsg);
		
		// 웹소켓으로 메시지 전송
		websocket.send(sendMsg);
		
		$(".chat-footer-row01").val("");
		
		/* 최근 채팅내역 변경 로직 추가 필요 (필요!) */
	}

	/* 파일 첨부 메시지 전송 메소드 */
	function sendMsgWithFiles(){
		// 파일 첨부 메시지
		console.log("파일 첨부 메시지");
		
		// 파일 첨부 메시지 JSON 정의
		chatMsgData.message_type = "File";
		chatMsgData.writer_id = loginUser;
		chatMsgData.chatroom_id = chatroomId;
		chatMsgData.write_time = new Date(new Date().getTime() + KR_TIME_DIFF);
		chatMsgData.message_content = $(".chat-footer-row01").val();
		chatMsgData.filenameList = filenameList;
		
		let sendMsg = JSON.stringify(chatMsgData);
		websocket.send(sendMsg);
		
		$.each(fileList, function(index, file){
			let fr = new FileReader();
			
			fr.onload = function(event){
				let arrayBuffer = this.result;
				console.log("arrayBuffer = " + arrayBuffer);
				websocket.send(arrayBuffer);
			}
			
			console.log("ArrayBuffer 전송!");
			fr.readAsArrayBuffer(file);
		})
		
		$(".chat-footer-row01").val("");
		fileList = [];
		filenameList = [];
	}

}) /* End of Ready Fn */