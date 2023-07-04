package com.multi.metahouse.dashboard.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.domain.entity.user.User;

public interface DashboardService {
	Page<User> findALL(Pageable pageable);
	List<User> findByUserIdLike(String keyword);
	int user_update(UserSearchResultDTO user);
}
