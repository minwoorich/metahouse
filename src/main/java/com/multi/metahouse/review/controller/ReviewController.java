package com.multi.metahouse.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReviewController {
@RequestMapping("/review/review")
public String review() {
	return "review/review";
}
}
