package com.multi.metahouse.user.repository.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.user.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUserIdAndPassword(String userId, String password);
	User findByUserId(String userId);
	User findBySocialLoginIdAndSocialName(String socialLoginId, String socialName);
	
	Page<User> findAll(Pageable pageable);
	List<User> findByUserIdLike(String keyword);
}
