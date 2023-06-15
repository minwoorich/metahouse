package com.multi.metahouse.project.service;



import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.project.ProjectDTO;

@Service
public interface ProjectService {
	int saveForm(ProjectDTO project);
	
}
