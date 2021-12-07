package mtsd.sam3.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mtsd.sam3.entities.Severity;
import mtsd.sam3.repository.SeverityRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SeverityRestController {
	
	@Autowired
	private SeverityRepository severityRepository;
	
	@GetMapping("/severity")
	public List<Severity> getSeverityList() {
		return severityRepository.findAll();
	}

}
