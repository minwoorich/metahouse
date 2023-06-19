package com.multi.metahouse.user.repository.dao;

import com.multi.metahouse.domain.entity.user.User;

public interface UserDAO {
	User login(String userId, String password);
	User insert(User user);
	int update(User user);
	void delete(String userId);
	User read(String userId);
}
