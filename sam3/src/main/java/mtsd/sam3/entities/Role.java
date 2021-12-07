package mtsd.sam3.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Role name is mandatory")
	@Size(max = 100)
	private String roleName;
	
	@NotNull
	private boolean upperManagement;
	
	public Role() {}


	public Role(@NotBlank(message = "Role name is mandatory") @Size(max = 100) String roleName,
			@NotNull boolean upperManagement) {
		super();
		this.roleName = roleName;
		this.upperManagement = upperManagement;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isUpperManagement() {
		return upperManagement;
	}

	public void setUpperManagement(boolean upperManagement) {
		this.upperManagement = upperManagement;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, roleName, upperManagement);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return id == other.id && Objects.equals(roleName, other.roleName) && upperManagement == other.upperManagement;
	}
	
	
	
	
}
