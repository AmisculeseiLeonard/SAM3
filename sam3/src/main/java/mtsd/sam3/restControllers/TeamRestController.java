package mtsd.sam3.restControllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mtsd.sam3.entities.Team;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.TeamRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@EnableAspectJAutoProxy
public class TeamRestController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/teams")
	public List<Team> getAllTeams(){
		return teamRepository.findAll();
	}
	
	@PostMapping("/teams")
	public Team createTeam(@RequestBody @Valid Team team) {
		Team savedTeam =  teamRepository.save(team);
		System.out.println( team.getEmployees());
		//savedTeam.getEmlpoyees().forEach(employee -> employee.addTeam(savedTeam));
		return savedTeam;
	}
	
	@GetMapping("teams/{id}")
	public ResponseEntity<Team> getTeamById(@PathVariable int id) {
		Team team = teamRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(team);
	}
	
	@PutMapping("/teams/{id}")
	public ResponseEntity<Team> updateTeam(@PathVariable int id,@RequestBody @Valid Team teamDetails) {
		Team team = teamRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " doesn't exist"));
		
		team.setId(teamDetails.getId());
		team.setName(teamDetails.getName());
		team.setProjects(teamDetails.getProjects());
		team.setTeamLeader(teamDetails.getTeamLeader());
		team.setEmployees(teamDetails.getEmployees());
		
		Team updatedTeam = teamRepository.save(team);
		return ResponseEntity.ok(updatedTeam);
		
	}
	
	@DeleteMapping("/Team/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTeam(@PathVariable int id) {
		Team product = teamRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Team with id: " + id + " doesn't exist"));
		
		teamRepository.delete(product);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
