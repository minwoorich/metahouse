package com.multi.metahouse.domain.dto.review;

public class ReviewContentsDTO {
    private int review_contents_id;
    private int review_id;
    private String review_store_filename;
    private Integer review_img_no;
	
    public ReviewContentsDTO() {
		super();
	}

	public ReviewContentsDTO(int review_contents_id, int review_id, String review_store_filename,
			Integer review_img_no) {
		super();
		this.review_contents_id = review_contents_id;
		this.review_id = review_id;
		this.review_store_filename = review_store_filename;
		this.review_img_no = review_img_no;
	}

	@Override
	public String toString() {
		return "ReviewContentsDTO [review_contents_id=" + review_contents_id + ", review_id=" + review_id
				+ ", review_store_filename=" + review_store_filename + ", review_img_no=" + review_img_no + "]";
	}

	public int getReview_contents_id() {
		return review_contents_id;
	}

	public void setReview_contents_id(int review_contents_id) {
		this.review_contents_id = review_contents_id;
	}

	public int getReview_id() {
		return review_id;
	}

	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getReview_store_filename() {
		return review_store_filename;
	}

	public void setReview_store_filename(String review_store_filename) {
		this.review_store_filename = review_store_filename;
	}

	public Integer getReview_img_no() {
		return review_img_no;
	}

	public void setReview_img_no(Integer review_img_no) {
		this.review_img_no = review_img_no;
	}
   
    
    
    
}
