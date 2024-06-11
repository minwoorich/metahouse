package com.multi.metahouse.search.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.search.repository.dao.SearchDAO;

import lombok.NoArgsConstructor;


@Service
@Transactional
@NoArgsConstructor
public class SearchServiceImpl implements SearchService{

	private SearchDAO dao;
	
	
	@Autowired
	public SearchServiceImpl(SearchDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter) {
		return dao.searchByFilter(filter);
	}

	@Override
	public List<UserSearchResultDTO> searchUserByKeyword(String keyword) {
		return dao.searchUserByKeyword(keyword);
	}

}
