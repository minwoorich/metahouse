package com.multi.metahouse.user.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.user.repository.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	UserDAO userDao;
	
	@Autowired
	public UserServiceImpl(UserDAO userDao) {
		super();
		this.userDao = userDao;
	}
	
	@Override
	public User login(String userId, String password) {
		// TODO Auto-generated method stub
		return userDao.login(userId, password);
	}

	@Override
	public User insert(User user) {
		user.setCreateDate(new Date());
		return userDao.insert(user);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return userDao.update(user);
	}

	@Override
	public void delete(String userId) {
		userDao.delete(userId);
	}

}
