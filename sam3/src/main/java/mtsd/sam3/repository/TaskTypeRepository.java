package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Integer>{

}
