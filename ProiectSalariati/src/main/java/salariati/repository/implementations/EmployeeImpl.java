package salariati.repository.implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.javaws.exceptions.InvalidArgumentException;
import salariati.exception.EmployeeException;

import salariati.model.Employee;

import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

public class EmployeeImpl implements EmployeeRepositoryInterface {
	
	private final String employeeDBFile = "employeeDB/employees.txt";
	private EmployeeValidator employeeValidator = new EmployeeValidator();

	@Override
	public boolean addEmployee(Employee employee) {
		if( employeeValidator.isValid(employee) ) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
				bw.write(employee.toString());
				bw.newLine();
				bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {

		List<Employee> employeeList = new ArrayList<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee;
				try {
					employee = Employee.getEmployeeFromString(line, counter);
					if (employee.getFirstName().equals(oldEmployee.getFirstName())){
						employee.setFunction(newEmployee.getFunction());
					}
					employeeList.add(employee);
				} catch(EmployeeException ex) {
					System.err.println("Error while reading: " + ex.toString());
				}
			}
		} catch (IOException e) {
			System.err.println("Error while reading: " + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error while closing the file: " + e);
				}
		}

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(employeeDBFile, false));
			for (Employee employee : employeeList) {
				bw.write(employee.toString());
				bw.newLine();
				bw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeeByName(String name) {
		List<Employee> employeeList = getEmployeeList();
		employeeList = employeeList.stream().filter(x-> x.getFirstName().equals(name)).collect(Collectors.toList());
		if (employeeList.isEmpty()){
			throw new IllegalArgumentException("name is invalid");
		}
		return employeeList.get(0);
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee = new Employee();
				try {
					employee = (Employee) Employee.getEmployeeFromString(line, counter);
					employeeList.add(employee);
				} catch(EmployeeException ex) {
					System.err.println("Error while reading: " + ex.toString());
				}
			}
		} catch (IOException e) {
			System.err.println("Error while reading: " + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error while closing the file: " + e);
				}
		}
		
		return employeeList;
	}


	@Override
	public List<Employee> getEmployeeByCriteria(String criteria) {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		return employeeList;
	}

}
