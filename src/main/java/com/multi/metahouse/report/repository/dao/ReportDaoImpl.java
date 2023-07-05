package com.multi.metahouse.report.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.report.ReportDTO;

@Repository
public class ReportDaoImpl implements ReportDao {
	SqlSession sqlSession;

	@Autowired
	public ReportDaoImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int insertReport(ReportDTO report) {
		return sqlSession.insert("com.multi.metahouse.report.reportProduct", report);
	}
}
