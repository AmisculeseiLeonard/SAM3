package mtsd.sam3.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import mtsd.sam3.entities.Product;

@Aspect
@Component
public class ProductControllerAspect {

	@After("execution(* mtsd.sam3.restControllers.ProductRestController.createProduct(..)) && args(product,..)")
	public void addProductIdToRelease(JoinPoint joinPoint, Product product) {
		if(!product.getReleases().isEmpty())
			product.getReleases().get(0).setProduct(product);
	}
}
