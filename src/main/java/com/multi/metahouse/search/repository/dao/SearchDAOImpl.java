package com.multi.metahouse.search.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Repository
public class SearchDAOImpl implements SearchDAO{

	private SqlSession ss;
	
	@Autowired
	public SearchDAOImpl(SqlSession ss) {
		this.ss = ss;
	}
	
	@Override
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter) {
		return ss.selectList("com.multi.metahouse.search.searchServiceByFilter", filter);
	}

	@Override
	public List<UserSearchResultDTO> searchUserByKeyword(String keyword) {
		return ss.selectList("com.multi.metahouse.search.searchUser",keyword);
	}

	@Override
	public int searchByFilterCount(SearchFilter filter) {
		return ss.selectOne("com.multi.metahouse.search.searchServiceByFilter", filter);
	}

	@Override
	public int searchUserByKeywordCount(String keyword) {
		return ss.selectOne("com.multi.metahouse.search.searchUser",keyword);
	}

}
