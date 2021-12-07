package mtsd.sam3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mtsd.sam3.entities.Product;
import mtsd.sam3.entities.Release;
import mtsd.sam3.exeption.ResourceNotFoundException;
import mtsd.sam3.repository.ProductRepository;

@Aspect
@Component
public class ReleaseControllerAspect {
	
	@Autowired
	ProductRepository productRepository; 
	
	@After("execution(* mtsd.sam3.restControllers.ReleaseRestController.createRelease(..)) && args(release,productId,..)")
	public void addReleaseToProduct(JoinPoint joinPoint, Release release, Integer productId) {

		
		
	}

}
