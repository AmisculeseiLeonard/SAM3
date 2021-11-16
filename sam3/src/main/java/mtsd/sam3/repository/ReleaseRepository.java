package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Release;

public interface ReleaseRepository extends JpaRepository<Release, Integer>{
	
}
