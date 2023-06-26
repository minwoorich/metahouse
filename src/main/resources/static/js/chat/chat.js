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

function createProfileElement(targetProfile){
	addPro =  '<img class="chat_body_profile-img" src="/metahaus/upload/userThumbnail/'+ targetProfile.thumbnail_store_filename + '">';
	addPro += '<div class="chat_body_profile-info">'+targetProfile.self_introduction+'</div>';
	
	return addPro;
}