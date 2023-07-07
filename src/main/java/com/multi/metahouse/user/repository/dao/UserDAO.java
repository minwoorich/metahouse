package com.multi.metahouse.user.repository.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.domain.entity.user.User;

public interface UserDAO {
	User login(String userId, String password);
	User insert(User user);
	User update(User user);
	void delete(String userId);
	User read(String userId);
	User socialLogin(String socialLoginId, String socialName);
	void updatePassword(String newPassword, String userId);
	Page<User> findAll(Pageable pageable);
	List<User> findByUserIdLike(String keyword);
	int user_update(UserSearchResultDTO user);
	boolean idcheck(String userId);
}
