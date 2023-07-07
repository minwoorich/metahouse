package com.multi.metahouse.review.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.multi.metahouse.domain.dto.report.ReportDTO;
import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;
import com.multi.metahouse.review.service.ReviewCommentService;

@Controller
public class ReviewCommentController {
	ReviewCommentService commentService;

	@Autowired
	public ReviewCommentController(ReviewCommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@RequestMapping("/reviewComment")
	@ResponseBody
	public void reviewcomment(@RequestBody ObjectNode saveObj) throws JsonProcessingException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		ReviewCommentDTO reviewComment =mapper.treeToValue(saveObj.get("comment"), ReviewCommentDTO.class);
		String tag = mapper.toString().valueOf(saveObj.get("tag"));
		System.out.println(reviewComment);
		commentService.saveReviewComment(reviewComment, tag);
	}
}
