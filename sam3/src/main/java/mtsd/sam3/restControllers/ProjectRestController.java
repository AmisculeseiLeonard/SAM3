package mtsd.sam3.restControllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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


import mtsd.sam3.entities.Project;
import mtsd.sam3.entities.Team;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.ProjectRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProjectRestController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@GetMapping("/projects")
	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}
	
	
	@PostMapping("/projects")
	public Project createProject(@RequestBody @Valid Project project) {
		return projectRepository.save(project);
	}
	
	@GetMapping("projects/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable int id) {
		Project project = projectRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(project);
	}
	
	
	@GetMapping("projects/{id}/teams")
	public ResponseEntity<List<Team>> getProjectTeamsById(@PathVariable int id) {
		Project project = projectRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " doesn't exist"));
		
		return ResponseEntity.ok(project.getTeams());
	}
	
	@PutMapping("/projects/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable int id,@RequestBody @Valid Project projectDetails) {
		Project project = projectRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " doesn't exist"));
		
		project.setBuildPriority(projectDetails.getBuildPriority());
		project.setBuildRelease(projectDetails.getBuildRelease());
		project.setDescription(projectDetails.getDescription());
		project.setProduct(projectDetails.getProduct());
		project.setProjectName(projectDetails.getProjectName());
		project.setCustomerTestDelivery(projectDetails.getCustomerTestDelivery());
		project.setFinishDevelop(projectDetails.getFinishDevelop());
		project.setFinishRelease(projectDetails.getFinishRelease());
		project.setProductionDelivery(projectDetails.getProductionDelivery());
		project.setReleaseForTesting(projectDetails.getReleaseForTesting());
		
		Project updatedProject = projectRepository.save(project);
		return ResponseEntity.ok(updatedProject);
		
	}
	
	@DeleteMapping("/projects/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable int id) {
		Project project = projectRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Project with id: " + id + " doesn't exist"));
		
		projectRepository.delete(project);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
