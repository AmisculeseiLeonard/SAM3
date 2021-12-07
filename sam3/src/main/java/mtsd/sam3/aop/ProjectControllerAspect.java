package mtsd.sam3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import mtsd.sam3.entities.Project;


@Aspect
@Component
public class ProjectControllerAspect {
	@After("execution(* mtsd.sam3.restControllers.ProjectRestController.createProject(..)) && args(project,..)")
	public void addTeamToEmployees(JoinPoint joinPoint, Project project) {
		
		project.getTeams().forEach(team -> team.addProject(project));
	}
}
