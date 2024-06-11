async function createChatElement(chatMsg, loginUser){
	console.log("현재 메시지 : " + chatMsg.message_id);

	// 채팅 메시지 요소
	let chatElement = '';

	// 채팅 Side 관련 요소
	let chatSideDiv = "";
	// 메시지 파일 요소
	let messageFileDiv = "";

	// 서버 파일 업로드 경로
	let filePath = "";
	// DB 파일정보 리스트
	let chatMsgFileList = [];

	try {
		const jsonData = await $.ajax({
		url: "/metahaus/chat/load/chatFile",
		type: "get",
		data: {
			chatMsgId: chatMsg.message_id,
		},
		dataType: "json"
		});

		// 메시지의 파일 리스트 받아서 저장
		filePath = jsonData.filePath;
		chatMsgFileList = jsonData.chatMsgFile;

			// 메시지 발신인 체크
			if(loginUser == chatMsg.writer_id){
				// 내 채팅
				chatSideDiv = '<div class="chat-block chat-block--send">' +
							'<div class="chat-block__message chat-block__message--send">';
			}else{
				// 상대방 채팅
				chatSideDiv = '<div class="chat-block chat-block--reception">' +
							'<div class="chat-block__message chat-block__message--reception">';
			}
		
			chatElement += chatSideDiv +
						'<div class="chat-block__message-text">' + chatMsg.message_content + '</div>' +
						'<div class="chat-block__message-files">';

		// 파일 첨부 메시지일 경우
		if(chatMsgFileList.length > 0){
			const files = await Promise.all(
				chatMsgFileList.map(async (file) => {
					const fileElement = await getURL(file.file_store_name);
					return (
					'<div class="message-file">' +
						fileElement +
					'<div>' +
						file.file_origin_name +
					"</div></div>"
					);
				})
			);
		
			chatElement += files.join("");
		}

		chatElement += '</div></div><div class="chat-block__timestamp">' +
					'<div class="chat-block__timestamp-date">' +
					chatMsg.write_time.substr(2, 8).replaceAll("-", ".") +
					'</div><div class="chat-block__timestamp-time">' +
					chatMsg.write_time.substr(11, 5) +
					'</div></div></div>';
	} catch (error) {
		/*console.error("Error during AJAX request:", error);*/
		alert("에러!");
	} finally{
		return chatElement;
	}
}

async function getURL(file_store_name){

	let returnElement = "";

	try{
		const url = "/metahaus/chat/getURL";
		const params = new URLSearchParams({
			fileStoreName: file_store_name,
		}).toString();

		const response = await fetch(`${url}?${params}`, {
			method: "get"
		});

		const jsonData = await response.json();

		returnElement += '<img class="file_img" src="/metahaus/upload/'+ file_store_name + '"/>';

	} catch (error){
		/*console.error("Error", error);*/
		alert("에러!");

	} finally{
		return returnElement;
	}

}

/*
 *  프로필 Elements 작성 메소드 
 */
function createProfileElement(targetProfile){
	let profile = '<div class="chat_body_profile-img_div">';	
	
	if(targetProfile.thumbnail_store_filename === null){
		profile += '<img class="chat_body_profile-img" src="/metahaus/images/test_img.jpg">';
	}else{
		profile += '<img class="chat_body_profile-img" src="/metahaus/upload/userThumbnail/' + targetProfile.thumbnail_store_filename + '">';
	}

	profile += '</div>'
		    +  '<div class="chat_body_profile-info">' 
		    +  targetProfile.self_introduction
		    +  '</div>';
	
	return profile;
}

/*
 * Blob 타입 확인 메소드
 */
function getFileNameFromBlob(blob) {
	// 파일 타입이 Blob 타입이 맞는지, Blob 파일이 name 속성을 갖는지 체크
	if (blob instanceof Blob && blob.name) {
		return blob.name;
	}
	
	// 파일 이름 확인 불가능할 경우
	return null;
}