package salariati.main;

import salariati.Ui.UI;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;
import salariati.service.EmployeeService;
import salariati.enumeration.DidacticFunction;

//functionalitati
//F01.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//F02.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//F03.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).

public class StartApp {
	
	public static void main(String[] args) {
		
//		EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
//		EmployeeService employeeService = new EmployeeService(employeesRepository);
//
//		for(Employee _employee : employeeService.getEmployeesList())
//			System.out.println(_employee.toString());
//		System.out.println("-----------------------------------------");
//
//		employeeService.addEmployee("FirstName","LastName", "1234567894321", DidacticFunction.ASISTENT, 500);
//
//		for(Employee _employee : employeeService.getEmployeesList())
//			System.out.println(_employee.toString());
//
//		EmployeeValidator validator = new EmployeeValidator();
//		System.out.println( validator.isValid(new Employee("FisrtNAme","LastName", "1234567894322", DidacticFunction.TEACHER, 3400)) );

		EmployeeRepositoryInterface repository = new EmployeeImpl();
		EmployeeService service = new EmployeeService(repository);
		UI ui = new UI(service);
		ui.showMenu();

	}

}
