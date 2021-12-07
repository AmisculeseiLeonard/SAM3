package mtsd.sam3.domainServices;

import java.util.Comparator;
import java.util.NoSuchElementException;

import mtsd.sam3.entities.Product;
import mtsd.sam3.entities.Release;

public class ProductServices {

	public ProductServices() {
		
	}
	
	public static Release getLatestRelease(Product product) {
		Integer maxVersion = product.getReleases()
		.stream()
		.max(Comparator.comparing(Release::getVersion))
		.orElseThrow(NoSuchElementException::new).getVersion();
		
		Release release = product.getReleases()
		.stream()
		.filter(r -> r.getVersion() == maxVersion)
		.max(Comparator.comparing(Release::getServicePack))
		.orElseThrow(NoSuchElementException::new);
		
		return release;
	}

	
}
