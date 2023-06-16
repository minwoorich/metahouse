package com.multi.metahouse.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewCommentController {
	@RequestMapping("/review/reviewComment")
	public String reviewcomment() {
		return "reviewComment/reviewComment";
	}
	}


