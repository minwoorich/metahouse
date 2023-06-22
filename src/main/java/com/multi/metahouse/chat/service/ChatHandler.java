package com.multi.metahouse.chat.service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value="/chat")
public class ChatHandler {

	private static Set<Session> clientset = Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void onOpen(Session client) {
		if(!clientset.contains(client)) {
			clientset.add(client);
			System.out.println("클라이언트가 접속했습니다:"+client);
		}
	}
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException {
		System.out.println("수신메세지:"+msg);
		//웹소켓에 접속한 모든 웹소켓클라이언트에게 메세지를 전송
		for(Session data:clientset) {
			System.out.println("전송메세지:"+msg);
			data.getBasicRemote().sendText(msg);
		}
	}
	@OnClose
	public void onClose(Session client) {
		System.out.println("접속종료"+client);
		clientset.remove(client);
	}
}
