package com.multi.metahouse.chat.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multi.metahouse.domain.dto.chat.ChatMsgDTO;

@Component
@ServerEndpoint(value="/chat")
public class ChatHandler extends AbstractWebSocketHandler{
	
	private static Set<Session> clientset = Collections.synchronizedSet(new HashSet<Session>());
	
	private static ChatService service;
	
	public ChatHandler() {
	}
	
	@Autowired
	public ChatHandler(ChatService service) {
		super();
		this.service = service;
	}

	@OnOpen
	public void onOpen(Session client) {
		if(!clientset.contains(client)) {
			clientset.add(client);
			System.out.println("클라이언트가 접속했습니다:"+client);
		}
	}
	
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		System.out.println("텍스트 수신메세지:"+msg);
		
		// 수신 메시지 DB에 저장
		ObjectMapper objectMapper = new ObjectMapper();
		ChatMsgDTO chatMsg = objectMapper.readValue(msg, ChatMsgDTO.class);
		
		System.out.println("chatMsg -> " + chatMsg);
		
		service.insertMessage(chatMsg);
		
		//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
		for(Session data:clientset) {
			System.out.println("전송메세지:"+msg);
			data.getBasicRemote().sendText(msg);
		}
	}
	
//	@Override
//	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
//		System.out.println("바이너리 메세지 수신: " + message);
//	}
	
	@OnMessage
	public void onMessage(ByteBuffer msg, Session session) throws IOException {
//		System.out.println("바이너리 수신 메세지:" + new String(msg.array()));
		System.out.println("바이너리 수신 메세지:" + msg);
		// 수신 메시지 DB에 저장
//		ObjectMapper objectMapper = new ObjectMapper();
//		ChatMsgDTO chatMsg = objectMapper.readValue(msg, ChatMsgDTO.class);
		
//		System.out.println("chatMsg -> " + chatMsg);
//		
//		service.insertMessage(chatMsg);
		
		//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
//		for(Session data:clientset) {
//			System.out.println("전송메세지:"+msg);
//			data.getBasicRemote().sendText(msg);
//		}
	}
	
	@OnClose
	public void onClose(Session client) {
		System.out.println("접속종료"+client);
		clientset.remove(client);
	}
	
	@OnError
	public void onError(Session session, Throwable th) {
		th.printStackTrace();
	}
}
