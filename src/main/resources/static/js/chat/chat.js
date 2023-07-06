$(document).ready(function(){
	// 세션 로그인 객체 저장 용도
	let loginUser = null;
	// chatroom 목록 elements로부터 채팅방 id 호출해 저장할 용도 
	let chatroomId = null;
	// 웹 소켓
	let ws = null;
	// Controller에 전송할 JSON
	var mydata = {};
	// 채팅 스크롤 관련
	var scrChatBody = $(".chat-body__chat-section");
	// 메시지 첨부 파일 관련 List
	let filelist = [];
	let filenamelist = [];
	// JSON 텍스트 메시지 저장 용도
	let resmsg = "";
	// 메시지 출력 item 저장 용도
	let item = "";
	// 파일 저장 용도
	let file_url = "";
	// 파일 개수 count 용도
	let file_count = 0;
	
	// 채팅방 목록 onClick eventListener
	$(".chatroom-card").on("click", async function(){
		// 숨김 처리되었던 채팅방 요소 보이기
		$(".chat-main").attr("style", "display = flex;");

		chatroomId = $(this).attr("id").replace("chatroom", "");
		
		loginUser = $("#loginUser").val();
		
		// 채팅방의 채팅 불러오기
		// $.ajax({
		// 	url:"/metahaus/chat/load/chat",
		// 	type:"get",
		// 	data:{
		// 		"chatroomId" : chatroomId,
		// 		"loginUser" : loginUser
		// 	},
		// 	dataType:"json",
		// 	success:function(jsonData){
		// 		console.log(jsonData);
				
		// 		let chatMsg = jsonData.chatMsg;
		// 		let targetProfile = jsonData.targetProfile;
				
		// 		// 채팅방 이름 설정
		// 		$(".chat-header").children("h1").text(targetProfile.user_name);
				
		// 		// 채팅 요소 작성
		// 		//let myChatEle = createChatElement(chatMsg, loginUser);
		// 		for(let i=0; i<chatMsg.length; i++){
		// 			createChatElement(chatMsg[i], loginUser);
		// 		}
		// 		// 테스트 채팅 요소 삭제
		// 		$(".chat-body__chat-section").children().remove();
		// 		// 채팅 요소 추가
		// 		//$(".chat-body__chat-section").append(myChatEle);
				
		// 		// 프로필 요소 작성
		// 		addPro = createProfileElement(targetProfile);
		// 		// 기존 프로필 요소 삭제
		// 		$(".chat_body_profile-img").remove();
		// 		$(".chat_body_profile-info").remove();
		// 		// 프로필 요소 추가
		// 		$(".chat-body__profile-section").append(addPro);
				
		// 		// 채팅 구현
		// 		if(!ws || ws.readyState !== WebSocket.OPEN){
		// 			ws = new WebSocket("ws://" + location.host + "/metahaus/chat");
		// 			console.log("web 소켓 생성 : " + ws);
		// 		}else{
		// 			console.log("open중인 web 소켓이 이미 존재합니다.");
		// 		}
				
		// 		// Server에 메시지 존재할 경우 수신
		// 		ws.onmessage = function(msg){
		// 			// String Type의 메시지를 수신한 경우 (JSON String)
		// 			if(typeof msg.data === "string"){
		// 				console.log("텍스트 메시지를 받았습니다.");
						
		// 				resmsg = JSON.parse(msg.data);
						
		// 				let msgcss = "";
		// 				let msgblkcss = "";
						
		// 				if(resmsg.writer_id==loginUser){
		// 					// 내 채팅
		// 					msgblkcss = "class='chat-block chat-block--send'";
		// 					msgcss = "class='chat-block__message chat-block__message--send'";
		// 				}else{
		// 					// 상대방 채팅
		// 					msgblkcss = "class='chat-block chat-block--reception'";
		// 					msgcss = "class='chat-block__message chat-block__message--reception'";
		// 				}
						

						
		// 				// 일반 텍스트 메시지인 경우
		// 				if(resmsg.message_type === "Text"){ 
		// 					console.log("일반 텍스트 메시지입니다.");
							
		// 					// 메시지 객체에 저장된 실제 데이터 꺼내기
		// 					item = '<div ' + msgblkcss + '><div ' + msgcss + '><div class="chat-block__message-text">';
		// 					item += resmsg.message_content;
		// 					item += '</div><div class="chat-block__message-files"></div></div><div class="chat-block__timestamp"><div class="chat-block__timestamp-date">';
		// 					item += resmsg.write_time.substr(2, 8).replaceAll("-", ".");
		// 					item += '</div><div class="chat-block__timestamp-time">';
		// 					item += resmsg.write_time.substr(11, 5);
		// 					item += '</div></div></div>';
							
		// 					$(".chat-body__chat-section").append(item);
						
		// 				// 파일이 첨부된 메시지인 경우
		// 				}else{	
		// 					console.log("파일이 첨부된 텍스트 메시지입니다. tot_file_count : " + resmsg.filenamelist.length);
							
		// 					item = '<div ' + msgblkcss + '><div ' + msgcss + '><div class="chat-block__message-text">';
		// 					item += resmsg.message_content;
		// 					item += '</div>';
		// 					item += '<div class="chat-block__message-files">';
		// 					file_count = 0; // 파일 개수 count
		// 				}
		// 			// Binary Type 의 메시지를 수신한 경우 1 (File 전송 - 해당 메시지의 마지막 파일을 수신한 경우)
		// 			}else if (msg.data instanceof Blob && (file_count+1) == resmsg.filenamelist.length){
		// 				console.log("마지막 바이너리 메시지를 받았습니다. file_count : " + file_count);
						
		// 				file_url = URL.createObjectURL(new Blob([msg.data]));
						
		// 				item += '<div class="message-file">';
		// 				item += '<img class="file_img" src="'+ file_url + '"/>';
		// 				item += '<div>'+resmsg.filenamelist[file_count]+'</div>';
		// 				item += '</div></div></div>';
		// 				item += '<div class="chat-block__timestamp"><div class="chat-block__timestamp-date">';
		// 				item += resmsg.write_time.substr(2, 8).replaceAll("-", ".");
		// 				item += '</div><div class="chat-block__timestamp-time">';
		// 				item += resmsg.write_time.substr(11, 5);
		// 				item += '</div></div>';
						
		// 				$(".chat-body__chat-section").append(item);
					
		// 			// Binary Type 의 메시지를 수신한 경우 2 (File 전송)
		// 			}else{
		// 				console.log("바이너리 메시지를 받았습니다. file_count : " + file_count);
						
		// 				file_url = URL.createObjectURL(new Blob([msg.data]));
						
		// 				item += '<div class="message-file">';
		// 				item += '<img class="file_img" src="'+ file_url + '"/>';
		// 				item += '<div>'+resmsg.filenamelist[file_count]+'</div>';
		// 				item += '</div>';
						
		// 				file_count++;
		// 			}
						
		// 			// 최근 메시지 변경
		// 			chatroom = "chatroom" + chatroomId;
		// 			$("#"+chatroom).find(".last-chat").text(resmsg.message_content);
					
		// 			/* 채팅 스크롤 항상 아래로 */
		// 			scrChatBody.scrollTop(scrChatBody[0].scrollHeight);
		// 		}
				
		// 		/* 채팅 스크롤 항상 아래로 */
		// 		scrChatBody.scrollTop(scrChatBody[0].scrollHeight);
			
		// 	} /* End of Success Fn */
			
		// }) /* End of Ajax */
		
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
		
			console.log(jsonData);
		
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
			addPro = createProfileElement(targetProfile);
		
			// 기존 프로필 요소 삭제
			$(".chat_body_profile-img").remove();
			$(".chat_body_profile-info").remove();
		
			// 프로필 요소 추가
			$(".chat-body__profile-section").append(addPro);
		
			// 채팅 구현
			if (!ws || ws.readyState !== WebSocket.OPEN) {
			  ws = new WebSocket("ws://" + location.host + "/metahaus/chat");
			  console.log("web 소켓 생성 : " + ws);
			} else {
			  console.log("open중인 web 소켓이 이미 존재합니다.");
			}
		
			// Server에 메시지 존재할 경우 수신
			ws.onmessage = function(msg){
				// String Type의 메시지를 수신한 경우 (JSON String)
				if(typeof msg.data === "string"){
					console.log("텍스트 메시지를 받았습니다.");
					
					resmsg = JSON.parse(msg.data);
					
					let msgcss = "";
					let msgblkcss = "";
					
					if(resmsg.writer_id==loginUser){
						// 내 채팅
						msgblkcss = "class='chat-block chat-block--send'";
						msgcss = "class='chat-block__message chat-block__message--send'";
					}else{
						// 상대방 채팅
						msgblkcss = "class='chat-block chat-block--reception'";
						msgcss = "class='chat-block__message chat-block__message--reception'";
					}
					

					
					// 일반 텍스트 메시지인 경우
					if(resmsg.message_type === "Text"){ 
						console.log("일반 텍스트 메시지입니다.");
						
						// 메시지 객체에 저장된 실제 데이터 꺼내기
						item = '<div ' + msgblkcss + '><div ' + msgcss + '><div class="chat-block__message-text">';
						item += resmsg.message_content;
						item += '</div><div class="chat-block__message-files"></div></div><div class="chat-block__timestamp"><div class="chat-block__timestamp-date">';
						item += resmsg.write_time.substr(2, 8).replaceAll("-", ".");
						item += '</div><div class="chat-block__timestamp-time">';
						item += resmsg.write_time.substr(11, 5);
						item += '</div></div></div>';
						
						$(".chat-body__chat-section").append(item);
					
					// 파일이 첨부된 메시지인 경우
					}else{	
						console.log("파일이 첨부된 텍스트 메시지입니다. tot_file_count : " + resmsg.filenamelist.length);
						
						item = '<div ' + msgblkcss + '><div ' + msgcss + '><div class="chat-block__message-text">';
						item += resmsg.message_content;
						item += '</div>';
						item += '<div class="chat-block__message-files">';
						file_count = 0; // 파일 개수 count
					}
				// Binary Type 의 메시지를 수신한 경우 1 (File 전송 - 해당 메시지의 마지막 파일을 수신한 경우)
				}else if (msg.data instanceof Blob && (file_count+1) == resmsg.filenamelist.length){
					console.log("마지막 바이너리 메시지를 받았습니다. file_count : " + file_count);
					
					file_url = URL.createObjectURL(new Blob([msg.data]));
					
					item += '<div class="message-file">';
					item += '<img class="file_img" src="'+ file_url + '"/>';
					item += '<div>'+resmsg.filenamelist[file_count]+'</div>';
					item += '</div></div></div>';
					item += '<div class="chat-block__timestamp"><div class="chat-block__timestamp-date">';
					item += resmsg.write_time.substr(2, 8).replaceAll("-", ".");
					item += '</div><div class="chat-block__timestamp-time">';
					item += resmsg.write_time.substr(11, 5);
					item += '</div></div>';
					
					$(".chat-body__chat-section").append(item);
				
				// Binary Type 의 메시지를 수신한 경우 2 (File 전송)
				}else{
					console.log("바이너리 메시지를 받았습니다. file_count : " + file_count);
					
					file_url = URL.createObjectURL(new Blob([msg.data]));
					
					item += '<div class="message-file">';
					item += '<img class="file_img" src="'+ file_url + '"/>';
					item += '<div>'+resmsg.filenamelist[file_count]+'</div>';
					item += '</div>';
					
					file_count++;
				}
					
				// 최근 메시지 변경
				chatroom = "chatroom" + chatroomId;
				$("#"+chatroom).find(".last-chat").text(resmsg.message_content);
				
				/* 채팅 스크롤 항상 아래로 */
				scrChatBody.scrollTop(scrChatBody[0].scrollHeight);
			}
			
			/* 채팅 스크롤 항상 아래로 */
			scrChatBody.scrollTop(scrChatBody[0].scrollHeight);
		
		  } catch (error) {
			console.error("Error during AJAX request:", error);
		  }
		// });

	}) /* End of Chatroom Click Event Fn */
	
	// 채팅 전송 이벤트 (버튼 클릭)
	$(".send-button").on("click", function(){
		if(filelist.length !== 0){
			sendMsgWithFiles();
		}else{
			sendMsg();
		}
	})
	
	// 채팅 전송 이벤트 (엔터 입력)
	$(".chat-footer-row01").on("keyup", function(){
		if(event.keyCode == 13){ // 엔터 입력시
			if(filelist.length !== 0){
				sendMsgWithFiles();
			}else{
				sendMsg();
			}
			
		}
	})
	
	// 웹소켓 종료 이벤트 (미구현)
	$("#btnclose").on("click", function(){
		// 웹 소켓 종료
		ws.close();
	})
	
	// 첨부 파일 업로드 이벤트
	$("#file_attach").on("change", function(){
		console.log("첨부파일 업로드 클릭!");
		let file = $(this)[0].files[0];
		let filename = getFileNameFromBlob(file);
		filelist.push(file);
		filenamelist.push(filename);
		console.log("filelist : " + filelist);
		console.log("filenamelist : " + filenamelist);
		
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
		mydata.message_type = "Text";
		mydata.writer_id = loginUser;
		mydata.chatroom_id = chatroomId;
		mydata.message_content = msg;
		mydata.write_time = new Date();
		
		let sendMsg = JSON.stringify(mydata); // json 문자열로 변환
		console.log("sendMsg : " + sendMsg);
		
		// 웹소켓으로 메시지 전송
		ws.send(sendMsg);
		
		$(".chat-footer-row01").val("");
		
		/* 최근 채팅내역 변경 로직 추가 필요 (필요!) */
	}

	/* 파일 첨부 메시지 전송 메소드 */
	function sendMsgWithFiles(){
		// 파일 첨부 메시지
		console.log("파일 첨부 메시지");
		
		// 파일 첨부 메시지 JSON 정의
		mydata.message_type = "File";
		mydata.writer_id = loginUser;
		mydata.chatroom_id = chatroomId;
		mydata.write_time = new Date();
		mydata.message_content = $(".chat-footer-row01").val();
		mydata.filenamelist = filenamelist;
		
		let sendMsg = JSON.stringify(mydata);
		ws.send(sendMsg);
		
		$.each(filelist, function(i, file){
			let fr = new FileReader();
			
			fr.onload = function(event){
				let arrayBuffer = this.result;
				console.log("arrayBuffer = " + arrayBuffer);
				ws.send(arrayBuffer);
			}
			
			console.log("ArrayBuffer 전송!");
			fr.readAsArrayBuffer(file);
		})
		
		$(".chat-footer-row01").val("");
		filelist = [];
		filenamelist = [];
	}

}) /* End of Ready Fn */