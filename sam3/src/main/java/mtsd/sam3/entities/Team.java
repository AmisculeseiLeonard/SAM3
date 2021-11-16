package mtsd.sam3.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Team implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Team name is mandatory")
	@Size(max = 100)
	private String name;
	
	@NotBlank(message = "Team desciption is mandatory")
	@Size(max = 250)
	private String teamDescription;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "leader_id")
	private Employee teamLeader;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "team_members",
	joinColumns = @JoinColumn(name="team_id"),
	inverseJoinColumns = @JoinColumn(name ="employee_id"))
	private List<Employee> employees;
	
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "team_project",
	joinColumns = @JoinColumn(name="team_id"),
	inverseJoinColumns = @JoinColumn(name ="project_id"))
	private List<Project> projects;
	

	public Team() {
		super();
	}

	
	public Team(@NotBlank(message = "Team name is mandatory") @Size(max = 100) String name,
			@NotBlank(message = "Team desciption is mandatory") @Size(max = 250) String teamDescription) {
		super();
		this.name = name;
		this.teamDescription = teamDescription;
	}


	public Team(@NotBlank(message = "Team name is mandatory") @Size(max = 100) String name,
			@NotBlank(message = "Team desciption is mandatory") @Size(max = 250) String teamDescription,
			Employee teamLeader, List<Employee> employees, List<Project> projects) {
		super();
		this.name = name;
		this.teamDescription = teamDescription;
		this.teamLeader = teamLeader;
		this.employees = employees;
		this.projects = projects;
	}


	public void addMember(Employee employee) {
		if(employees == null) {
			employees = new ArrayList<Employee>();
		}
		employees.add(employee);
	}
	
	public void addProject(Project project) {
		if(projects == null) {
			projects = new ArrayList<Project>();
		}
		projects.add(project);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Employee getTeamLeader() {
		return teamLeader;
	}


	public void setTeamLeader(Employee teamLeader) {
		this.teamLeader = teamLeader;
	}


	public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}


	public List<Project> getProjects() {
		return projects;
	}


	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}


	public String getTeamDescription() {
		return teamDescription;
	}


	public void setTeamDescription(String teamDescription) {
		this.teamDescription = teamDescription;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
