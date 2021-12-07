package mtsd.sam3.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtsd.sam3.entities.BuildPriority;
import mtsd.sam3.repository.BuildPriorityRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BuildPriorityRestController {
	
	@Autowired
	private BuildPriorityRepository buildPriorityRepository;
	
	@GetMapping("/buildPriorities")
	public List<BuildPriority> getAllBuildPriorities(){
		return buildPriorityRepository.findAll();
	}

}
