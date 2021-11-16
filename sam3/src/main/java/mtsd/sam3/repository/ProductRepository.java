package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
