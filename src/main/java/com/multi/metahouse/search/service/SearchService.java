package com.multi.metahouse.search.service;

import java.util.List;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;

public interface SearchService {
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter);
}
