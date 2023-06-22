package com.multi.metahouse.user.service;

import com.multi.metahouse.domain.entity.user.User;

public interface UserService {
	User login(String userId, String password);
	User insert(User user);
	User update(User user);
	void delete(String userId);
}
