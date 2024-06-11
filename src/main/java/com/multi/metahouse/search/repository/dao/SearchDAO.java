package com.multi.metahouse.search.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;

public interface SearchDAO {
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter);
	public List<UserSearchResultDTO> searchUserByKeyword(String keyword);
	
	public int searchByFilterCount(SearchFilter filter);
	public int searchUserByKeywordCount(String keyword);
}
