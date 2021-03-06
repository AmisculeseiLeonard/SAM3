package mtsd.sam3.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Project name is mandatory")
	@Size(max = 100)
	private String projectName;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Size(max = 500)
	private String description;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "build_priority_id")
	private BuildPriority buildPriority;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "team_project",
	joinColumns = @JoinColumn(name="project_id"),
	inverseJoinColumns = @JoinColumn(name ="team_id"))
	private List<Team> teams;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Task> tasks;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "build_release_id")
	private Release buildRelease;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "finish_release_id")
	private Release finishRelease;
	
	private Date finishDevelop;
	
	private Date releaseForTesting;
	
	private Date customerTestDelivery;
	
	private Date productionDelivery;

	public Project() {
		super();
	}

	
	
	public Project(@NotBlank(message = "Project name is mandatory") @Size(max = 100) String projectName,
			Product product, @Size(max = 500) String description, BuildPriority buildPriority) {
		super();
		this.projectName = projectName;
		this.product = product;
		this.description = description;
		this.buildPriority = buildPriority;
	}



	public void addTeam(Team team) {
		if(teams == null) {
			teams = new ArrayList<Team>();
		}
		teams.add(team);
	}
	
	public void addTask(Task task) {
		if(tasks == null) {
			tasks = new ArrayList<Task>();
		}
		tasks.add(task);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BuildPriority getBuildPriority() {
		return buildPriority;
	}

	public void setBuildPriority(BuildPriority buildPriority) {
		this.buildPriority = buildPriority;
	}

	public Date getFinishDevelop() {
		return finishDevelop;
	}

	public void setFinishDevelop(Date finishDevelop) {
		this.finishDevelop = finishDevelop;
	}

	public Date getReleaseForTesting() {
		return releaseForTesting;
	}

	public void setReleaseForTesting(Date releaseForTesting) {
		this.releaseForTesting = releaseForTesting;
	}

	public Date getCustomerTestDelivery() {
		return customerTestDelivery;
	}

	public void setCustomerTestDelivery(Date customerTestDelivery) {
		this.customerTestDelivery = customerTestDelivery;
	}

	public Date getProductionDelivery() {
		return productionDelivery;
	}
	
	public void setProductionDelivery(Date productionDelivery) {
		this.productionDelivery = productionDelivery;
	}
	
	public List<Team> getTeams() {
		return teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Release getBuildRelease() {
		return buildRelease;
	}

	public void setBuildRelease(Release buildRelease) {
		this.buildRelease = buildRelease;
	}

	public Release getFinishRelease() {
		return finishRelease;
	}

	public void setFinishRelease(Release finishRelease) {
		this.finishRelease = finishRelease;
	}
	
	
	
	 
	
	
	
	
	
}
