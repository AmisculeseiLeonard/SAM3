package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Severity;

public interface SeverityRepository extends JpaRepository<Severity, Integer>{

}
