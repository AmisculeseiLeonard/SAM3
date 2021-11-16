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
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody @Valid Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping("products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id) {
		Product product = productRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody @Valid Product productDetails) {
		Product product = productRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
		
		product.setId(productDetails.getId());
		product.setDescription(productDetails.getDescription());
		product.setProductName(productDetails.getProductName());
		product.setReleases(productDetails.getReleases());
		
		Product updatedProduct = productRepository.save(product);
		return ResponseEntity.ok(updatedProduct);
		
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable int id) {
		Product product = productRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exist"));
		
		productRepository.delete(product);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
