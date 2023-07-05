package com.multi.metahouse.user.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.user.OtherProfileInfoDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.project.repository.dao.ProjectDAO;
import com.multi.metahouse.user.repository.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	UserDAO userDao;
	AssetDAO assetDao;
	ProjectDAO projectDao;
	
	@Autowired
	public UserServiceImpl(UserDAO userDao, AssetDAO assetDao, ProjectDAO projectDao) {
		super();
		this.userDao = userDao;
		this.assetDao = assetDao;
		this.projectDao = projectDao;
	}
	
	@Override
	public User login(String userId, String password) {
		// TODO Auto-generated method stub
		return userDao.login(userId, password);
	}

	@Override
	public User insert(User user) {
		user.setCreateDate(new Date());
		System.out.println("회원가입 진행중 - service");
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

	@Override
	@Transactional
	public OtherProfileInfoDTO read(String userId) {
		User userInfo = userDao.read(userId);
		List<AssetDTO> assetInfo = assetDao.findBySellerId(userId);
		List<ProjectDTO> projectInfo = projectDao.findByCreatorId(userId);
		
		OtherProfileInfoDTO otherProfileInfo = new OtherProfileInfoDTO(userInfo, assetInfo, projectInfo);
		
		return otherProfileInfo;
	}

	@Override
	public User readUserInfo(String userId) {
		// TODO Auto-generated method stub
		return userDao.read(userId);
	}

	@Override
	public User socialLogin(String socialLoginId, String socialName) {
		return userDao.socialLogin(socialLoginId, socialName);
	}
}
