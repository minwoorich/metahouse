package com.multi.metahouse.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewContentsController {
	@RequestMapping("/review/reviewContets")
		public String reviewcontets() {
			return "reviewcontets/reviewcontets";
		}
	}

