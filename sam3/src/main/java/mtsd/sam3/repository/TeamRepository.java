package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{
	
}
