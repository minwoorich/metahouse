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