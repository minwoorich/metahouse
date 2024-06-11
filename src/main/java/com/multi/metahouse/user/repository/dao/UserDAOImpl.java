package com.multi.metahouse.user.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.user.repository.jpa.UserRepository;


@Repository
public class UserDAOImpl implements UserDAO {
	UserRepository repository;
	SqlSession ss;
	
	@Autowired
	public UserDAOImpl(UserRepository repository , SqlSession ss) {
		super();
		this.repository = repository;
		this.ss = ss;
	}

	@Override
	public User login(String userId, String password) {
		// TODO Auto-generated method stub
		return repository.findByUserIdAndPassword(userId, password);
	}

	@Override
	public User insert(User user) {
		System.out.println("회원가입 진행중 - dao");
		return repository.save(user);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		
		User table = repository.findById(user.getUserId()).orElseThrow(() -> new RuntimeException());
		table.setEmail(user.getEmail());
		table.setUserName(user.getUserName());
		table.setPhoneNumber(user.getPhoneNumber());
		table.setGender(user.getGender());
		table.setBirth(user.getBirth());
		table.setSelfIntroduction(user.getSelfIntroduction());
		table.setMkAgree(user.isMkAgree());
		if(!(user.getThumbnailStoreFilename().isEmpty())) {
			table.setThumbnailStoreFilename(user.getThumbnailStoreFilename());
		}
		
		repository.save(table);
		return table;
	}

	@Override
	public void delete(String userId) {
		repository.deleteById(userId);
	}

	@Override
	public User read(String userId) {
		return repository.findByUserId(userId);
	}
	
	@Override
	public User socialLogin(String socialLoginId, String socialName) {
		return repository.findBySocialLoginIdAndSocialName(socialLoginId, socialName);
	}

	@Override
	public void updatePassword(String newPassword, String userId) {
		User table = repository.findById(userId).orElseThrow(() -> new RuntimeException());
		table.setPassword(newPassword);
		
		repository.save(table);
	}

	public Page<User> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<User> findByUserIdLike(String keyword) {
		return repository.findByUserIdLike(keyword);
	}

	@Override
	public int user_update(UserSearchResultDTO user) {
		return ss.update("com.multi.metahouse.member.updateUser", user);
	}

	@Override
	public boolean idcheck(String userId) {
		boolean result = false;
		User user = repository.findByUserId(userId);
		System.out.println(user);
		
		if(user != null) {
			result = true;
		}
		
		return result;
	}
	

}
