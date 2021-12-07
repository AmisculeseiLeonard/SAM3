package mtsd.sam3.restControllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtsd.sam3.entities.Product;
import mtsd.sam3.entities.Release;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.ProductRepository;
import mtsd.sam3.repository.ReleaseRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReleaseRestController {

	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Autowired
	private ProductRepository productRepository; 
	
	@GetMapping("/releases")
	public List<Release> getAllReleases(){
		return releaseRepository.findAll();
	}
	
	@GetMapping("/releasesByProductId/{id}")
	public List<Release> getReleasesByProductId(@PathVariable int id){
		return releaseRepository.findByProductId(id);
	}
	
	@PostMapping("/releases")
	public Release createRelease(@RequestBody @Valid Release release, Integer productId) {
		return releaseRepository.save(release);
	}
	
	@PostMapping("/releases/{id}")
	public Release createReleaseForSpecificProduct(@PathVariable int id, @RequestBody @Valid Release release) {
		Product product = productRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
		product.addRelease(release);
		release.setProduct(product);
		return releaseRepository.save(release);
	}
	
	
	@GetMapping("releases/{id}")
	public ResponseEntity<Release> getReleaseById(@PathVariable int id) {
		Release release = releaseRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Release with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(release);
	}
	
	@PutMapping("/releases/{id}")
	public ResponseEntity<Release> updateRelease(@PathVariable int id,@RequestBody @Valid Release releaseDetails) {
		Release release = releaseRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Release with id: " + id + " doesn't exist"));
		
		release.setId(releaseDetails.getId());
		release.setDescription(releaseDetails.getDescription());
		release.setProduct(releaseDetails.getProduct());
		release.setServicePack(releaseDetails.getServicePack());
		release.setVersion(releaseDetails.getVersion());
		
		Release updatedRelease = releaseRepository.save(release);
		return ResponseEntity.ok(updatedRelease);
		
	}
	
	@DeleteMapping("/releases/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteRelease(@PathVariable int id) {
		Release release = releaseRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Release with id: " + id + " doesn't exist"));
		
		releaseRepository.delete(release);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
