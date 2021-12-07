package mtsd.sam3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mtsd.sam3.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
