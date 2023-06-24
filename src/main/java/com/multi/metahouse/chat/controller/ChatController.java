package com.multi.metahouse.chat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.chat.service.ChatService;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;
import com.multi.metahouse.domain.dto.chat.ChatroomDTO;
import com.multi.metahouse.domain.entity.user.User;

@Controller
@RequestMapping("/chat")
public class ChatController {
	
	ChatService service;
	
	@Autowired
	public ChatController(ChatService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public ModelAndView viewChatroom(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		User loginUser = (User)session.getAttribute("loginUser");
		//Map<String, Object> chat_view_data = service.getChatroomView(loginUser.getUserId());
		//mav.addObject("chatrooms", chat_view_data.get("chatrooms"));
		//mav.addObject("chatrooms_last_msg", chat_view_data.get("chatrooms_last_msg"));

		List<ChatroomDTO> chatrooms = service.getChatroomView(loginUser.getUserId());
		mav.addObject("chatrooms", chatrooms);
		mav.setViewName("chat/chat");
		
		return mav;
	}
	
	@GetMapping("/load/chat")
	public String loadChat(int chatroomId) {
		List<ChatMsgDTO> chats = service.getChatMsgById(chatroomId);
		return "chat/chat";
	}
	
}
