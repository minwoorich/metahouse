/* 프로필 Elements 작성 메소드 */
function createProfileElement(targetProfile){
	addPro =  '<img class="chat_body_profile-img" src="/metahaus/upload/userThumbnail/'+ targetProfile.thumbnail_store_filename + '">';
	addPro += '<div class="chat_body_profile-info">'+targetProfile.self_introduction+'</div>';
	
	return addPro;
}

/* 채팅메시지 Elements 작성 메소드 */
function createChatElement(chatMsg, loginUser){
	for(let i=0; i<chatMsg.length; i++){
		// 파일 첨부 메시지여부 확인
//		if(chatMsg[i].message_type === "File"){
//			console.log("파일 첨부 메시지 확인");
		$.ajax({
			url:"/metahaus/chat/load/chatFile",
			type:"get",
			data:{
				"chatMsgId" : chatMsg[i].message_id
			},
			dataType:"json",
			success:function(jsonData){
				console.log(jsonData);
				
				myChatEle = '';
				
				let chatSideDiv = "";
				let messageFileDiv = "";
				
				// 메시지 발신인 체크
				if(loginUser == chatMsg[i].writer_id){
					// 내 채팅
					chatSideDiv = '<div class="chat-block chat-block--send">' +
		            			  '<div class="chat-block__message chat-block__message--send">';
				}else{
					// 상대방 채팅
					chatSideDiv = '<div class="chat-block chat-block--reception">' + 
					 			  '<div class="chat-block__message chat-block__message--reception">';
				}
				
				myChatEle += chatSideDiv + 
							 '<div class="chat-block__message-text">' + chatMsg[i].message_content + '</div>' + 
							 '<div class="chat-block__message-files">';
				
				// 메시지의 파일 리스트 받아서 저장
				let chatMsgFileList = jsonData.chatMsgFile;
				let fileList = jsonData.fileList;
//				let fileArrayBufferList = [];
//				
//				for(let i=0; i<fileList.length; i++){
//					console.log("fileList[" + (i+1) + "] : " + fileList[i]);
//					const uint8Array = Uint8Array.from(fileList[i]);
//					
//					fileArrayBufferList.push(uint8Array.buffer);
//				}
				
//				console.log(fileArrayBufferList);
				
//				const arrayBufferList = fileList.map((byteBuffer) => {
//					const uint8Array = new Uint8Array(byteBuffer);
//					console.log("uint8Array : " + uint8Array);
//					return uint8Array.buffer;
//				});
				
//				console.log("checkpoint : " + arrayBufferList);
				
				const uint8ArrayList = fileList.map((base64String) => {
					const binaryString = atob(base64String);
					const uint8Array = new Uint8Array(binaryString.length);
					for(let i=0; i<binaryString.length; i++){
						uint8Array[i] = binaryString.charCodeAt(i);
					}
					return uint8Array;
				});
				
//				console.log("checkpoint : " + uint8ArrayList[0]);
				
//				const uint8ArrayList = [];
//				
//				for(let i=0; i<fileList.length; i++){
//					const binaryString = atob(fileList[i]);
//					const uint8Array = new Uint8Array(binaryString.length);
//					for(let j=0; j<binaryString.length; j++){
//						uint8Array[j] = binaryString.charCodeAt(j);
//					}
//					uint8ArrayList.push(uint8Array);
//				}
				
//				console.log(uint8ArrayList);
//				console.log(arrayBufferList);
				
				for(let i=0; i<chatMsgFileList.length; i++){
					console.log(chatMsgFileList[i]);
					// 파일 이미지 생성
					console.log("파일 이미지 생성");
//					console.log(arrayBufferList[i]);
					let fileURL = URL.createObjectURL(new Blob([uint8ArrayList.pop()]));
					myChatEle += '<img class="file_img" src="'+ fileURL + '"/>';
					myChatEle += '<span>'+chatMsgFileList[i].file_origin_name+'</span>';
				}
				
				myChatEle += '</div></div><div class="chat-block__timestamp">' +
	 			 '<div class="chat-block__timestamp-date">' + 
	 			  chatMsg[i].write_time.substr(2, 8).replaceAll("-", ".") +
	 			 '</div><div class="chat-block__timestamp-time">' +
	 			  chatMsg[i].write_time.substr(11, 5) +
	 			 '</div></div></div>';
				
				console.log("파일 이미지 생성 후 element : " + myChatEle);
				
				$(".chat-body__chat-section").append(myChatEle);
			}
			
		})
	}
//	return myChatEle;
}

function getFileNameFromBlob(blob) {
	if (blob instanceof Blob && blob.name) {
		// 파일 타입이 Blob 타입이 맞는지, Blob 파일이 name 속성을 갖는지 체크
		return blob.name;
	}
	
	// 파일 이름 확인 불가능할 경우
	return null;
}