package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.BuildPriority;

public interface BuildPriorityRepository extends JpaRepository<BuildPriority, Integer>{

}
