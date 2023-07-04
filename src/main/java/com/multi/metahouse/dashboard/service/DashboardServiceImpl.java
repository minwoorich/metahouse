package com.multi.metahouse.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.user.repository.dao.UserDAO;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class DashboardServiceImpl implements DashboardService{
	
	private UserDAO dao;
	
	
	@Autowired
	public DashboardServiceImpl(UserDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Page<User> findALL(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<User> findByUserIdLike(String keyword) {
		return dao.findByUserIdLike(keyword);
	}

	@Override
	public int user_update(UserSearchResultDTO user) {
		return dao.user_update(user);
	}
	
}
