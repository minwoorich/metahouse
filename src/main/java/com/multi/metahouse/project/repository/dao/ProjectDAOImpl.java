package com.multi.metahouse.project.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;

public class ProjectDAOImpl implements ProjectDAO {
	SqlSession sqlSession;
	String namespace = "";
	@Autowired
	public ProjectDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}


	@Override
	public int insertFile(List<ProjectContentsDTO> detailImageList) {
	
		return 0;
	}

}
