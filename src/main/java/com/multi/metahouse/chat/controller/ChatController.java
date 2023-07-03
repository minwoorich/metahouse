package com.multi.metahouse.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.chat.service.ChatService;
import com.multi.metahouse.domain.dto.chat.ChatProfileDTO;
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

	/* 채팅방 메인 페이지 */
	@RequestMapping("/")
	public ModelAndView viewChatroom(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		User loginUser = (User)session.getAttribute("loginUser");
		List<ChatroomDTO> chatrooms = service.getChatroomView(loginUser.getUserId());
		List<ChatProfileDTO> profiles = service.getProfileList(chatrooms, loginUser.getUserId());
		
		mav.addObject("chatrooms", chatrooms);
		mav.addObject("loginUser", loginUser);
		mav.addObject("profiles", profiles);
		
		mav.setViewName("chat/chat");
		
		return mav;
	}
	
	/* 채팅방 생성 */
	@GetMapping("/create/chatroom")
	public String createChatroom(HttpSession session, String seller_id) {
		User loginUser = (User)session.getAttribute("loginUser");
		String user_1_id = loginUser.getUserId();
		String user_2_id = seller_id;
		
		service.createChatroom(user_1_id, user_2_id);
		
		return "redirect:/chat/";
	}
	
	/* 채팅방 메시지, 상대방 프로필 조회 */
	@GetMapping(value = "/load/chat", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> loadChat(HttpSession session, int chatroomId, String loginUser) {
		Map<String, Object> chatmsgJSON = new HashMap<>();
		
		// 채팅 메시지 콘텐츠 리스트
		chatmsgJSON.put("chatMsg", service.getChatMsgById(chatroomId));
		// 상대방 프로필 리스트
		chatmsgJSON.put("targetProfile", service.getProfileById(chatroomId, loginUser));
		
		return chatmsgJSON;
	}
	
}
