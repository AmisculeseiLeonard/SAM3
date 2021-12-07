package mtsd.sam3.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Task name is mandatory")
	@Size(max = 100)
	private String taskName;
	
	@Size(max = 500)
	private String taskDescription;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "task_type_id")
	private TaskType taskType;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "task_status_id")
	private TaskStatus taskStatus;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "priority_id")
	private BuildPriority priority; 
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "severity_id")
	private Severity severity;
	
	@Future(message = "Due date cannot be a past date")
	private Date dueDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "assigned_employee_id")
	private Employee assignedEmployee;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "project_id")
	@JsonBackReference
	private Project project;

	public Task() {
		super();
	}

	

	public Task(@NotBlank(message = "Task name is mandatory") @Size(max = 100) String taskName,
			@Size(max = 500) String taskDescription, TaskType taskType, TaskStatus taskStatus, BuildPriority priority,
			Severity severity, @Future(message = "Due date cannot be a past date") Date dueDate,
			Employee assignedEmployee, Project project) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskType = taskType;
		this.taskStatus = taskStatus;
		this.priority = priority;
		this.severity = severity;
		this.dueDate = dueDate;
		this.assignedEmployee = assignedEmployee;
		this.project = project;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}


	public BuildPriority getPriority() {
		return priority;
	}

	public void setPriority(BuildPriority priority) {
		this.priority = priority;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Employee getAssignedEmployee() {
		return assignedEmployee;
	}

	public void setAssignedEmployee(Employee assignedEmployee) {
		this.assignedEmployee = assignedEmployee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
	
}
