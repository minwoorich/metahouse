package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

public class ReviewCommentDTO {
    private int review_comment_id;
    private String writer_id;
    private int review_id;
    private String comment_text;
    private Timestamp comment_date;
	
    
    public ReviewCommentDTO() {
		super();
	}


	public ReviewCommentDTO(int review_comment_id, String writer_id, int review_id, String comment_text,
			Timestamp comment_date) {
		super();
		this.review_comment_id = review_comment_id;
		this.writer_id = writer_id;
		this.review_id = review_id;
		this.comment_text = comment_text;
		this.comment_date = comment_date;
	}


	@Override
	public String toString() {
		return "ReviewCommentDTO [review_comment_id=" + review_comment_id + ", writer_id=" + writer_id + ", review_id="
				+ review_id + ", comment_text=" + comment_text + ", comment_date=" + comment_date + "]";
	}


	public int getReview_comment_id() {
		return review_comment_id;
	}


	public void setReview_comment_id(int review_comment_id) {
		this.review_comment_id = review_comment_id;
	}


	public String getWriter_id() {
		return writer_id;
	}


	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}


	public int getReview_id() {
		return review_id;
	}


	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}


	public String getComment_text() {
		return comment_text;
	}


	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}


	public Timestamp getComment_date() {
		return comment_date;
	}


	public void setComment_date(Timestamp comment_date) {
		this.comment_date = comment_date;
	}
    
    
    
}

