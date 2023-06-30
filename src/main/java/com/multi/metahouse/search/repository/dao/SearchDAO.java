package com.multi.metahouse.search.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;

public interface SearchDAO {
	public List<ServiceSearchResultDTO> searchByFilter(SearchFilter filter);
}
