package com.multi.metahouse.domain.dto;

import java.sql.Timestamp;

public class ChatroomDTO {
    private int chatroom_id;
    private String user_1_id;
    private String user_2_id;
    private String tag;
    private Timestamp open_Date;
    private String seller_info;
	
    
    public ChatroomDTO() {
		super();
	}


	@Override
	public String toString() {
		return "ChatroomDTO [chatroom_id=" + chatroom_id + ", user_1_id=" + user_1_id + ", user_2_id=" + user_2_id
				+ ", tag=" + tag + ", open_Date=" + open_Date + ", seller_info=" + seller_info + "]";
	}


	public ChatroomDTO(int chatroom_id, String user_1_id, String user_2_id, String tag, Timestamp open_Date,
			String seller_info) {
		super();
		this.chatroom_id = chatroom_id;
		this.user_1_id = user_1_id;
		this.user_2_id = user_2_id;
		this.tag = tag;
		this.open_Date = open_Date;
		this.seller_info = seller_info;
	}


	public int getChatroom_id() {
		return chatroom_id;
	}


	public void setChatroom_id(int chatroom_id) {
		this.chatroom_id = chatroom_id;
	}


	public String getUser_1_id() {
		return user_1_id;
	}


	public void setUser_1_id(String user_1_id) {
		this.user_1_id = user_1_id;
	}


	public String getUser_2_id() {
		return user_2_id;
	}


	public void setUser_2_id(String user_2_id) {
		this.user_2_id = user_2_id;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public Timestamp getOpen_Date() {
		return open_Date;
	}


	public void setOpen_Date(Timestamp open_Date) {
		this.open_Date = open_Date;
	}


	public String getSeller_info() {
		return seller_info;
	}


	public void setSeller_info(String seller_info) {
		this.seller_info = seller_info;
	}
	
    
    
}
