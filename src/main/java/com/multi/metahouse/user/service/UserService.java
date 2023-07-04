package com.multi.metahouse.user.service;

import com.multi.metahouse.domain.dto.user.OtherProfileInfoDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;

public interface UserService {
	User login(String userId, String password);
	User insert(User user);
	User update(User user);
	void delete(String userId);
	OtherProfileInfoDTO read(String userId);
	User readUserInfo(String userId);
	User socialLogin(String socialLoginId, String socialName);
}
