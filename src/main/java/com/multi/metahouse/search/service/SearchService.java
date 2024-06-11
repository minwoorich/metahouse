package com.multi.metahouse.search.service;

import java.util.List;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;

public interface SearchService {
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter);
	public List<UserSearchResultDTO> searchUserByKeyword(String keyword);
}
