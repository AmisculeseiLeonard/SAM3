package mtsd.sam3.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message = "Product name is mandatory")
	@Size(max = 100)
	private String productName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	@JsonManagedReference
	private List<Release> releases;
	
	@Size(max = 500)
	private String description;

	
	public Product() {
		super();
	}


	public Product(@NotBlank(message = "Product name is mandatory") @Size(max = 100) String productName,
			@Size(max = 500) String description) {
		super();
		this.productName = productName;
		this.description = description;
	}


	public void addRelease(Release release) {
		if(releases == null) {
			releases = new ArrayList<Release>();
		}
		releases.add(release);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public List<Release> getReleases() {
		return releases;
	}


	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	
	

	
	
	
	
	

}
