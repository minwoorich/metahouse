package com.multi.metahouse.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.report.ReportDTO;
import com.multi.metahouse.report.repository.dao.ReportDao;

@Service
public class ReportServiceImpl implements ReportService {
	ReportDao dao;

	@Autowired
	public ReportServiceImpl(ReportDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public int reportProduct(ReportDTO report) {
		return dao.insertReport(report);
	}
}
