package mtsd.sam3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mtsd.sam3.entities.Release;

public interface ReleaseRepository extends JpaRepository<Release, Integer>{
	
	@Query(value = "select * from release where product_id = :id",
			nativeQuery=true)
	public List<Release> findByProductId(@Param(value = "id") Integer id);
}
