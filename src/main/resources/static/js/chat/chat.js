$(document).ready(function(){
	// 세션 로그인 객체 저장 용도
	let loginUser = null;
	// chatroom 목록 elements로부터 채팅방 id 호출해 저장할 용도 
	let chatroomId = null;
	// 웹 소켓
	let ws = null;
	// Controller에 전송할 JSON
	var mydata = {};
	// 메시지 첨부 파일 관련 List
	let filelist = [];
	let filenamelist = [];
	
	// 채팅방 목록 onClick eventListener
	$(".chatroom-card").on("click", function(){
		// 숨김 처리되었던 채팅방 요소 보이기
		$(".chat-main").attr("style", "display = flex;");

		chatroomId = $(this).attr("id").replace("chatroom", "");
		
		loginUser = $("#loginUser").val();
		
		// 채팅방의 채팅 불러오기
		$.ajax({
			url:"/metahaus/chat/load/chat",
			type:"get",
			data:{
				"chatroomId" : chatroomId,
				"loginUser" : loginUser
			},
			dataType:"json",
			success:function(jsonData){
				console.log(jsonData);
				
				let chatMsg = jsonData.chatMsg;
				let targetProfile = jsonData.targetProfile;
				
				// 채팅방 이름 설정
				$(".chat-header").children("h1").text(targetProfile.user_name);
				
				// 채팅 요소 작성
				let myChatEle = createChatElement(chatMsg, loginUser);
				// 테스트 채팅 요소 삭제
				$(".chat-body__chat-section").children().remove();
				// 채팅 요소 추가
				$(".chat-body__chat-section").append(myChatEle);
				
				// 프로필 요소 작성
				addPro = createProfileElement(targetProfile);
				// 기존 프로필 요소 삭제
				$(".chat_body_profile-img").remove();
				$(".chat_body_profile-info").remove();
				// 프로필 요소 추가
				$(".chat-body__profile-section").append(addPro);
				
				// 채팅 구현
				if(!ws || ws.readyState !== WebSocket.OPEN){
					ws = new WebSocket("ws://" + location.host + "/metahaus/chat");
					console.log("web 소켓 생성 : " + ws);
				}else{
					console.log("open중인 web 소켓이 이미 존재합니다.");
				}
				
				ws.onmessage = function(msg){
					let resmsg = JSON.parse(msg.data);
					let msgcss = "";
					
					if(resmsg.writer_id==loginUser){
						// 내 채팅
						msgblkcss = "class='chat-block chat-block--send'";
						msgcss = "class='chat-block__message chat-block__message--send'";
					}else{
						// 상대방 채팅
						msgblkcss = "class='chat-block chat-block--reception'";
						msgcss = "class='chat-block__message chat-block__message--reception'";
					}
					// 메시지 객체에 저장된 실제 데이터 꺼내기
					let item = '<div ' + msgblkcss + '><div ' + msgcss + '><div class="chat-block__message-text">';
						item += resmsg.message_content;
						item += '</div><div class="chat-block__message-files"></div></div><div class="chat-block__timestamp"><div class="chat-block__timestamp-date">';
						item += resmsg.write_time.substr(2, 8).replaceAll("-", ".");
						item += '</div><div class="chat-block__timestamp-time">';
						item += resmsg.write_time.substr(11, 5);
						item += '</div></div></div>';
						
					$(".chat-body__chat-section").append(item);

					// 최근 메시지 변경
					chatroom = "chatroom" + chatroomId;
					$("#"+chatroom).find(".last-chat").text(resmsg.message_content);
					
				}
				
				/* 채팅 스크롤 항상 아래로 */
				let scrChatBody = $(".chat-body__chat-section");
				scrChatBody.scrollTop(scrChatBody[0].scrollHeight);
			}
			
		})
		
	})
	
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
	
	/* 일반 메시지 전송 메소드 */
	function sendMsg(){
		console.log("일반 메시지");
		let msg = $(".chat-footer-row01").val();
		 
		// 서버로 보낼 메시지를 만들기
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


	/* 프로필 Elements 작성 메소드 */
	function createProfileElement(targetProfile){
		addPro =  '<img class="chat_body_profile-img" src="/metahaus/upload/userThumbnail/'+ targetProfile.thumbnail_store_filename + '">';
		addPro += '<div class="chat_body_profile-info">'+targetProfile.self_introduction+'</div>';
		
		return addPro;
	}

	/* 채팅메시지 Elements 작성 메소드 */
	function createChatElement(chatMsg, loginUser){
		myChatEle = '';
		
		for(let i=0; i<chatMsg.length; i++){
			if(loginUser == chatMsg[i].writer_id){
				// 내 채팅
				myChatEle += '<div class="chat-block chat-block--send">' +
		                     '<div class="chat-block__message chat-block__message--send">' +
		                     '<div class="chat-block__message-text">' +
		                     	chatMsg[i].message_content +
		            		 '</div><div class="chat-block__message-files"></div></div>' + 
		        			 '<div class="chat-block__timestamp">' +
		            		 '<div class="chat-block__timestamp-date">' +
		            		 	chatMsg[i].write_time.substr(2, 8).replaceAll("-", ".") +
		        			 '</div><div class="chat-block__timestamp-time">' +
		        			 	chatMsg[i].write_time.substr(11, 5) +
		        			 '</div></div></div>';
			}else{
				// 상대방 채팅
				myChatEle += '<div class="chat-block chat-block--reception">' + 
	        				 '<div class="chat-block__message chat-block__message--reception">' +
	            			 '<div class="chat-block__message-text">' +
	            			 	chatMsg[i].message_content +
	            			 '</div><div class="chat-block__message-files"></div></div>' +
	        				 '<div class="chat-block__timestamp">' +
				             '<div class="chat-block__timestamp-date">' + 
				              	chatMsg[i].write_time.substr(2, 8).replaceAll("-", ".") +
				             '</div><div class="chat-block__timestamp-time">' +
				             	chatMsg[i].write_time.substr(11, 5) +
				             '</div></div></div>';
			}
		}
		
		return myChatEle;
	}
	
	function getFileNameFromBlob(blob) {
		if (blob instanceof Blob && blob.name) {
			// 파일 타입이 Blob 타입이 맞는지, Blob 파일이 name 속성을 갖는지 체크
			return blob.name;
		}
		
		// 파일 이름 확인 불가능할 경우
		return null;
	}
})