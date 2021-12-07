package mtsd.sam3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import mtsd.sam3.entities.Team;

@Aspect
@Component
public class TeamControllerAspect {
	
	@After("execution(* mtsd.sam3.restControllers.TeamRestController.createTeam(..)) && args(team,..)")
	public void addTeamToEmployees(JoinPoint joinPoint, Team team) {
		team.getEmployees().forEach(member -> member.addTeam(team));
		//team.getEmployees().forEach(member -> System.out.println(member.getTeams().contains(team)));
	}
}
