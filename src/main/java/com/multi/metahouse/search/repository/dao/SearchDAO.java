package com.multi.metahouse.search.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.user.UserDTO;

public interface SearchDAO {
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter);
	public List<UserDTO> searchUserByKeyword(String keyword);
	
	public int searchByFilterCount(SearchFilter filter);
	public int searchUserByKeywordCount(String keyword);
}
