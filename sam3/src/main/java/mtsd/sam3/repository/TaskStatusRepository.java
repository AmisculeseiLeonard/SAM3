package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.TaskStatus;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer>{

}
