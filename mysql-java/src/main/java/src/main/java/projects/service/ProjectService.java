package src.main.java.projects.service;

import projects.dao.ProjectDao;
import projects.entity.Project;
import projects.exception.DbException;

public class ProjectService {
	
	private ProjectDao projectDao = new ProjectDao();
	
	
	
	

	public Project addProject(Project project) {
		return projectDao.insertProject(project);	
	}

}
