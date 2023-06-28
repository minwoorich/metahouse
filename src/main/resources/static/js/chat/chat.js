$(document).ready(function(){
	let loginUser = null;
	let chatroomId = null;
	// 채팅방의 onClick eventListener
	let mydata = {};
	$(".chatroom-card").on("click", function(){
		chatroomId = $(this).attr("id").replace("chatroom", "");
		
		console.log(chatroomId);
		
		// 채팅방 채팅 불러오기
		$.ajax({
			url:"/metahaus/chat/load/chat",
			type:"get",
			data:{
				"chatroomId" : chatroomId
			},
			dataType:"json",
			success:function(jsonData){
				console.log(jsonData);
				
				let chatMsg = jsonData.chatMsg;
				loginUser = jsonData.loginUser;
				
				// 채팅 요소 작성
				let myChatEle = createChatElement(chatMsg, loginUser);
				// 테스트 채팅 요소 삭제
				$(".chat-body__chat-section").children().remove();
				// 채팅 요소 추가
				$(".chat-body__chat-section").append(myChatEle);
				
				// 프로필 요소 작성
				addPro = createProfileElement(jsonData.targetProfile);
				// 기존 프로필 요소 삭제
				$(".chat_body_profile-img").remove();
				$(".chat_body_profile-info").remove();
				// 프로필 요소 추가
				$(".chat-body__profile-section").append(addPro);
				
				// 채팅 구현
				ws = new WebSocket("ws://"+ location.host +"/metahaus/chat");
				
				console.log("web 소켓 생성 : " + ws);
				
				ws.onmessage = function(msg){
					console.log(msg);
					let resmsg = JSON.parse(msg.data);
					console.log(resmsg);
					let msgcss = "";
					if(resmsg.id==$("loginUser").val()){
						// 내 채팅
						msgcss = "class=chat-block chat-block--send";
					}else{
						// 상대방 채팅
						msgcss = "class=chat-block chat-block--reception";
					}
					// 메시지 객체에 저장된 실제 데이터 꺼내기
					let item = '<div ' + msgcss + '><div class="chat-block__message chat-block__message--send"><div class="chat-block__message-text">';
					item += resmsg.msg;
					item += resmsg.date + "]</b></br>";
					
					$("#talklist").append(item);
				}
				// 웹소켓이 연결된 후에
				ws.onopen = function(msg){
					$("#talklist").append("server start...\n");
				}
				// 웹소켓이 종료된 후에
				ws.onclose = function(msg){
					$("#talklist").append("server disconnect...\n");
				}
				// 웹소켓 사용 중 에러가 발생하는 경우
				ws.onerror = function(msg){
					$("#talklist").append("error...\n");
				}
			}
			
		})
		
	})
	
	$(".send-button").on("click", function(){
		sendMsg(); 
	})
	
	$(".chat-footer-row01").on("keyup", function(){
		if(event.keyCode == 13){ // 엔터 입력시
			sendMsg();
		}
	})
	
	$("#btnclose").on("click", function(){
		// 웹 소켓 종료
		ws.close();
	})
	
	//메시지 전송
	function sendMsg(){
		let msg = $(".chat-footer-row01").val();
		 
		// 서버로 보낼 메시지를 만들기
		// 사용자 아이디, 지금은 input 태그에 입력한 것을 가져오지만 나중에는 세션에서 아이디 꺼내서 전달
		mydata.writer_id = loginUser.userId;
		mydata.chatroom_id = chatroomId;
		mydata.message_content = msg;
		mydata.write_time = new Date();
		//mydata.write_time = new Date().toLocaleString();
		let sendMsg = JSON.stringify(mydata); // json 문자열로 변환
		
		// 웹소켓으로 메시지 전송
		ws.send(sendMsg);
		$(".chat-footer-row01").val("");
	}
	
	function createProfileElement(targetProfile){
		addPro =  '<img class="chat_body_profile-img" src="/metahaus/upload/userThumbnail/'+ targetProfile.thumbnail_store_filename + '">';
		addPro += '<div class="chat_body_profile-info">'+targetProfile.self_introduction+'</div>';
		
		return addPro;
	}

	function createChatElement(chatMsg, loginUser){
		myChatEle = '';
		
		for(let i=0; i<chatMsg.length; i++){
			if(loginUser.userId == chatMsg[i].writer_id){
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
})



/*
document.addEventListener('DOMContentLoaded', function() {
	function sendMessage() {
		var input = document.querySelector('.chat-footer-row01');
		var message = input.value.trim();

		if (message !== '') {
			var chatSection = document.querySelector('.chat-body__chat-section');
			var newMessage = document.createElement('div');
			newMessage.classList.add('message');
			newMessage.textContent = message;
			chatSection.appendChild(newMessage);

			input.value = '';
		}
	} 

	var sendButton = document.querySelector('.send-button');
	sendButton.addEventListener('click', sendMessage);

	var input = document.querySelector('.chat-footer-row01');
	input.addEventListener('keydown', function(event) {
	  	if (event.key === 'Enter' && !event.shiftKey) {
    		event.preventDefault();
      		sendMessage();
		}
	});
});
*/