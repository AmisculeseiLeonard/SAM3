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


import mtsd.sam3.entities.Task;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TaskRestController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	@PostMapping("/tasks")
	public Task createTask(@RequestBody @Valid Task team) {
		Task savedTask =  taskRepository.save(team);
		return savedTask;
	}
	
	@GetMapping("tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable int id) {
		Task task = taskRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(task);
	}
	
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable int id,@RequestBody @Valid Task taskDetails) {
		Task task = taskRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " doesn't exist"));
		
		task.setAssignedEmployee(taskDetails.getAssignedEmployee());
		task.setDueDate(taskDetails.getDueDate());
		task.setPriority(taskDetails.getPriority());
		task.setSeverity(taskDetails.getSeverity());
		task.setTaskDescription(taskDetails.getTaskDescription());
		task.setTaskName(taskDetails.getTaskName());
		task.setTaskStatus(taskDetails.getTaskStatus());
		task.setTaskType(taskDetails.getTaskType());
		
		Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
		
	}
	
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable int id) {
		Task product = taskRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " doesn't exist"));
		
		taskRepository.delete(product);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
