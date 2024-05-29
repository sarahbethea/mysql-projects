package src.main.java.projects;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.dao.DbConnection;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

@SuppressWarnings("unused")


public class ProjectsApp {
	
	
	
	Scanner scanner = new Scanner(System.in);
	
	// @formatter:off
	private List<String> operations = List.of(
		"1) Add a project"
	
		);
	// @formatter: on
	
	ProjectService projectService = new ProjectService();
	
	
	
	

	public static void main(String[] args) {
		
	//	 DbConnection.getConnection();
		
		new ProjectsApp().processUserSelections();
		
	

	}
	
	
	
	private void processUserSelections() {
		boolean done = false;
		
		
		
		while (!done) {
			try {
				int selection = getUserSelection();
				switch(selection) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createProject();
					break;
				default: 
					System.out.println("\nSelection " + selection + " is not a valid selection. Try again.");
				}
				
			}
			catch (Exception e) {
				System.out.println("\nError: " + e + "Try again.");
			}
			
		}
		
	}



	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer diffculty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(diffculty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
		
	}



	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);
		
		if (Objects.isNull(input)) {
			return null;
		}
		
		try {
			return new BigDecimal(input).setScale(2);		}
		catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	
	}



	private boolean exitMenu() {
		// TODO Auto-generated method stub
		return false;
	}



	private int getUserSelection() {
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection.");
		
		return Objects.isNull(input) ? -1 : input;
		
	}



	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);
		
		if (Objects.isNull(input)) {
			return null;
		}
		
		try {
			return Integer.valueOf(input);
		}
		catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	
	}



	private String getStringInput(String prompt) {
		System.out.println(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}



	private void printOperations() {
		System.out.print("\nThese are the available selections. Press the Enter key to quit:");
		System.out.println();
		operations.forEach(line -> System.out.println("   " + line));
		
		
	}

}
