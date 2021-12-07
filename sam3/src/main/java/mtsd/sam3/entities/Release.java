package mtsd.sam3.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Release {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@NotNull(message = "Version cannot be null")
	private int version;
	
	@NotNull(message = "Service pack cannot be null")
	private int servicePack;
	
	@Size(max = 500)
	private String description;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.REFRESH})
	
	@JsonBackReference
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	public Release() {
		super();
	}

	

	public Release(@NotBlank(message = "Version cannot be null") int version,
			@NotBlank(message = "Service pack cannot be null") int servicePack, @Size(max = 500) String description,
			Product product) {
		super();
		this.version = version;
		this.servicePack = servicePack;
		this.description = description;
		this.product = product;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}



	public int getServicePack() {
		return servicePack;
	}



	public void setServicePack(int servicePack) {
		this.servicePack = servicePack;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	
	
	
	
	
	
	
	

}
