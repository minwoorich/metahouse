package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

public class ReviewDTO {
	private int review_id;
	private String writer_id;
	private String order_tag;
	private Integer rating;
	private String review_text;
	private Timestamp review_date;

	public ReviewDTO() {
		super();
	}

	public ReviewDTO(int review_id, String writer_id, String order_tag, Integer rating, String review_text,
			Timestamp review_date) {
		super();
		this.review_id = review_id;
		this.writer_id = writer_id;
		this.order_tag = order_tag;
		this.rating = rating;
		this.review_text = review_text;
		this.review_date = review_date;
	}

	@Override
	public String toString() {
		return "ReviewDTO [review_id=" + review_id + ", writer_id=" + writer_id + ", order_tag=" + order_tag
				+ ", rating=" + rating + ", review_text=" + review_text + ", review_date=" + review_date + "]";
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getOrder_tag() {
		return order_tag;
	}

	public void setOrder_tag(String order_tag) {
		this.order_tag = order_tag;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReview_text() {
		return review_text;
	}

	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}

	public Timestamp getReview_date() {
		return review_date;
	}

	public void setReview_date(Timestamp review_date) {
		this.review_date = review_date;
	}

}
