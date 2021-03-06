package mtsd.sam3.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TaskStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Task status name is mandatory")
	@Size(max = 100)
	private String taskStatusName;

	public TaskStatus() {
		super();
	}

	

	public TaskStatus(@NotBlank(message = "Task status name is mandatory") @Size(max = 100) String taskStatusName) {
		super();
		this.taskStatusName = taskStatusName;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}
	
	
}
