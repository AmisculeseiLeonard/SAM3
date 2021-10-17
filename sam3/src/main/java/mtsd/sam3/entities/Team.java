package mtsd.sam3.entities;

import java.util.ArrayList;
import java.util.List;

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

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "leader_id")
	private Employee teamLeader;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "team_members",
	joinColumns = @JoinColumn(name="team_id"),
	inverseJoinColumns = @JoinColumn(name ="employee_id"))
	private List<Employee> emlpoyees;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "team_project",
	joinColumns = @JoinColumn(name="team_id"),
	inverseJoinColumns = @JoinColumn(name ="project_id"))
	private List<Project> projects;
	

	public Team() {
		super();
	}


	public Team(String name, Employee teamLeader, List<Employee> emlpoyees) {
		this.name = name;
		this.teamLeader = teamLeader;
		this.emlpoyees = emlpoyees;
	}
	
	public void addMember(Employee employee) {
		if(emlpoyees == null) {
			emlpoyees = new ArrayList<Employee>();
		}
		emlpoyees.add(employee);
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


	public List<Employee> getEmlpoyees() {
		return emlpoyees;
	}


	public void setEmlpoyees(List<Employee> emlpoyees) {
		this.emlpoyees = emlpoyees;
	}


	public List<Project> getProjects() {
		return projects;
	}


	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	
	
	
}
