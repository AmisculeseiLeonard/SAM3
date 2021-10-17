package mtsd.sam3.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Priority {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String priorityName;

	public Priority() {
		super();
	}

	public Priority(String priorityName) {
		super();
		this.priorityName = priorityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	
	
}
