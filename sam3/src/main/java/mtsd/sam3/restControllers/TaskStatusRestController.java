package mtsd.sam3.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mtsd.sam3.entities.TaskStatus;
import mtsd.sam3.repository.TaskStatusRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskStatusRestController {
	
	@Autowired
	private TaskStatusRepository taskStatusRepository;
	
	@GetMapping("/task-statuses")
	public List<TaskStatus> getAllTaskStatuses() {
		return taskStatusRepository.findAll();
	}

}
