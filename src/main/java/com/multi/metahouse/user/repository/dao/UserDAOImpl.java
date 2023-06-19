package com.multi.metahouse.user.repository.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.user.repository.jpa.UserRepository;


@Repository
public class UserDAOImpl implements UserDAO {
	UserRepository repository;
	
	@Autowired
	public UserDAOImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public User login(String userId, String password) {
		// TODO Auto-generated method stub
		return repository.findByUserIdAndPassword(userId, password);
	}

	@Override
	public User insert(User user) {
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

}
