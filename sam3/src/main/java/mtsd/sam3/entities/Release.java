package mtsd.sam3.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Release {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Version cannot be null")
	private String version;
	@NotBlank(message = "Service pack cannot be null")
	private String servicePack;
	
	@Size(max = 500)
	private String description;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	public Release() {
		super();
	}

	

	public Release(@NotBlank(message = "Version cannot be null") String version,
			@NotBlank(message = "Service pack cannot be null") String servicePack, @Size(max = 500) String description,
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getServicePack() {
		return servicePack;
	}

	public void setServicePack(String servicePack) {
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
