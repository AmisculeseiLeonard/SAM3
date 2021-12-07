package mtsd.sam3.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mtsd.sam3.entities.TaskType;
import mtsd.sam3.repository.TaskTypeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskTypeRestController {
	
	@Autowired
	private TaskTypeRepository taskTypeRepository;
	
	@GetMapping("/task-types")
	public List<TaskType> getAllTaskTypes() {
		return taskTypeRepository.findAll();
	}

}
