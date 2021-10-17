package mtsd.sam3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mtsd.sam3.dao.EmployeeDaoImplementation;
import mtsd.sam3.entities.Employee;

@SpringBootApplication
public class Sam3Application {

	public static void main(String[] args) {
		SpringApplication.run(Sam3Application.class, args);
		
//		EmployeeDaoImplementation dao = new EmployeeDaoImplementation();
//		
//		
//		Employee employee = dao.FindAll().get(0);
//		System.out.println((employee));
	}

}
