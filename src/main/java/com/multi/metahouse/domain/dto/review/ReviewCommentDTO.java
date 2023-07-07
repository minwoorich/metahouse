package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("comment")
public class ReviewCommentDTO {
	private int review_comment_id;
	private int review_id;
	private String comment_writer_id;
	private String comment_text;
	private Timestamp comment_date;

}
