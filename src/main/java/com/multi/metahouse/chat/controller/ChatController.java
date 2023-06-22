package com.multi.metahouse.chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.chat.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	ChatService service;
	
	@RequestMapping("/")
	public String viewChatroom() {
		return "chat/chat";
	}
	
	@GetMapping("/load/chatroom")
	public String loadChatroom(HttpSession session) {
		
		
		return "";
	}
	
}
