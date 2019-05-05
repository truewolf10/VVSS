package salariati.integration;

import org.junit.Before;
import org.junit.Test;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.service.EmployeeService;
import salariati.validator.EmployeeValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("Duplicates")
public class Big_bang_Integration {

    private EmployeeService controller;
    private EmployeeValidator employeeValidator;
    private static final int line = 2;

    @Before
    public void setUp() {
        EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
        controller         = new EmployeeService(employeeRepository);
        employeeValidator  = new EmployeeValidator();
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void testAddNewEmployee() {
        Employee newEmployee = new Employee("ValidFirstName","val", "1910509055057", DidacticFunction.ASISTENT, 3000);
        assertTrue(employeeValidator.isValid(newEmployee));
        controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
        assertEquals(1, controller.getEmployeesList().size());
        assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
    }

    @Test
    public void testGetEmployeeFromString_03() throws EmployeeException {
        String _employee = "andrei;popica;1970701314011;ASISTENT;2000";
        Employee employee = Employee.getEmployeeFromString(_employee, line);
        assertEquals(DidacticFunction.ASISTENT, employee.getFunction());
    }

    @Test
    public void getSortedEmployeeListValid() {
        controller.addEmployee("ValidFirstName1","vasile", "1910509055057", DidacticFunction.ASISTENT, 3000);
        controller.addEmployee("ValidFirstName2","vasile", "1910509055057", DidacticFunction.ASISTENT, 2000);
        controller.addEmployee("ValidFirstName3","vasile", "1930509055057", DidacticFunction.ASISTENT, 2000);
        List<Employee> employees = controller.getSortedEmployeeList();
        assertEquals("ValidFirstName1", employees.get(0).getFirstName());
        assertEquals("ValidFirstName3", employees.get(1).getFirstName());
        assertEquals("ValidFirstName2", employees.get(2).getFirstName());
    }

    @Test
    public void big_bang_integration() throws IOException {
        Path employeePath = Paths.get("employeeDB/employees.txt");
        Path employeePathBkp = Paths.get("employeeDB/employees.txt.barb");
        saveFile(employeePath, employeePathBkp);

        EmployeeRepositoryInterface repo = new EmployeeImpl();
        EmployeeService service = new EmployeeService(repo);

        service.addEmployee("ValidFirstName1","vasile", "1910509055057", DidacticFunction.ASISTENT, 3000);
        assertEquals(1, service.getEmployeesList().size());

        service.modifyEmployee("ValidFirstName1", DidacticFunction.LECTURER);
        List<Employee> employeeList = service.getEmployeesList();
        assertEquals(DidacticFunction.LECTURER, employeeList.get(0).getFunction());

        service.addEmployee("ValidFirstName2","vasile", "1910509055057", DidacticFunction.ASISTENT, 4000);
        assertEquals(2, service.getEmployeesList().size());
        List<Employee> employeeListSorted = service.getSortedEmployeeList();
        assertEquals("ValidFirstName2", employeeListSorted.get(0).getFirstName());
        assertEquals("ValidFirstName1", employeeListSorted.get(1).getFirstName());

        reloadFile(employeePathBkp, employeePath);
    }

    private void reloadFile(Path activitiesBackupFilePath, Path activitiesFilePath) throws IOException {
        if (Files.exists(activitiesFilePath)) {
            Files.delete(activitiesFilePath);
        }
        if (Files.exists(activitiesBackupFilePath)) {
            Files.move(activitiesBackupFilePath, activitiesFilePath);
        } else {
            Files.createFile(activitiesFilePath);
        }
    }

    private void saveFile(Path activitiesFilePath, Path activitiesBackupFilePath) throws IOException {
        if (Files.exists(activitiesFilePath)) {
            Files.move(activitiesFilePath, activitiesBackupFilePath);
        }
        Files.createFile(activitiesFilePath);
    }
}
