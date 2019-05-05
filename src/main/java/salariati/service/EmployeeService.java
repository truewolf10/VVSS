package salariati.service;

import java.time.Year;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

public class EmployeeService {
	
	private EmployeeRepositoryInterface employeeRepository;
	
	public EmployeeService(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void addEmployee(String firstName, String lastName, String cnp, DidacticFunction didacticFunction, float salary) {
		Employee employee = new Employee();
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setCnp(cnp);
		employee.setFunction(didacticFunction);
		employee.setSalary(salary);
		employeeRepository.addEmployee(employee);
	}
	
	public List<Employee> getEmployeesList() {
		return employeeRepository.getEmployeeList();
	}
	
	public void modifyEmployee(String name, DidacticFunction didacticFunction) {
		Employee oldEmployee = employeeRepository.getEmployeeByName(name);
		Employee newEmployee = new Employee();
		newEmployee.setFunction(didacticFunction);
		employeeRepository.modifyEmployee(oldEmployee, newEmployee);
	}

	public List<Employee> getSortedEmployeeList(){
		List<Employee> employeeList = employeeRepository.getEmployeeList();
		Comparator<Employee> comparator = Comparator.comparing(Employee::getSalary).reversed();
		comparator = comparator.thenComparing((e1) -> {
			int year = Year.now().getValue();
			int yearOfBirth = Integer.valueOf(e1.getCnp().substring(1,3));
			if (yearOfBirth < 21)
				yearOfBirth += 2000;
			else
				yearOfBirth += 1900;
			return year-yearOfBirth;
		});
		employeeList.sort(comparator);
		return employeeList;


	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.deleteEmployee(employee);
	}

}
