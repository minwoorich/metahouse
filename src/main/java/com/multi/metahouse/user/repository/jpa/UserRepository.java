package com.multi.metahouse.user.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.user.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUserIdAndPassword(String userId, String password);
}
