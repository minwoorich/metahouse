package com.multi.metahouse.report.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.multi.metahouse.domain.dto.report.ReportDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.report.service.ReportService;

@Controller
public class ReportController {
	ReportService service;

	@Autowired
	public ReportController(ReportService service) {
		super();
		this.service = service;
	}

	@RequestMapping(value = "/report")
	@ResponseBody
	public String reportProduct(@RequestBody ObjectNode saveObj, HttpSession session)
			throws JsonProcessingException, IllegalArgumentException {
		ObjectMapper mapper = new ObjectMapper();
		ReportDTO report = mapper.treeToValue(saveObj.get("report"), ReportDTO.class);
		User user = (User) session.getAttribute("loginUser");
		report.setUser_id(user.getUserId());
		System.out.println(report);
		int result = service.reportProduct(report);
		String data = null;

		if (result == 1) {
			data = "신고가 접수되었습니다.";
		} else {
			data = "신고 접수에 실패했습니다. 다시 시도해주세요";
		}

		return data;
	}

}
