package mtsd.sam3.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mtsd.sam3.entities.Employee;

@Repository
public class EmployeeDaoImplementation implements EmployeeDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Autowired
//	public EmployeeDaoImplementation(EntityManager entityManager) {
//		super();
//		this.entityManager = entityManager;
//	}


	@Override
	@Transactional
	public List<Employee> FindAll() {
		Session session = entityManager.unwrap(Session.class);
		
		List<Employee> employees = 
				session.createQuery("from Employee", Employee.class).getResultList();
		
		return employees;
	}

}
