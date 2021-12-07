package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
