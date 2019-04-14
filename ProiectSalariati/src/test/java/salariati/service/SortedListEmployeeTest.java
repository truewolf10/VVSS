package salariati.service;

import org.junit.Before;
import org.junit.Test;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryCustomPopicaMock;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;

import java.util.List;

import static org.junit.Assert.*;

public class SortedListEmployeeTest {

    private EmployeeService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSortedEmployeeListValid() {
        EmployeeRepositoryInterface repo = new EmployeeMock();
        service = new EmployeeService(repo);
        service.addEmployee("ValidFirstName1","vasile", "1910509055057", DidacticFunction.ASISTENT, 3000);
        service.addEmployee("ValidFirstName2","vasile", "1910509055057", DidacticFunction.ASISTENT, 2000);
        service.addEmployee("ValidFirstName3","vasile", "1930509055057", DidacticFunction.ASISTENT, 2000);
        List<Employee> employees = service.getSortedEmployeeList();
        assertEquals("ValidFirstName1", employees.get(0).getFirstName());
        assertEquals("ValidFirstName3", employees.get(1).getFirstName());
        assertEquals("ValidFirstName2", employees.get(2).getFirstName());
    }

    @Test
    public void getSortedEmployeeListNonValid(){
        EmployeeRepositoryInterface repo = new EmployeeRepositoryCustomPopicaMock();
        service = new EmployeeService(repo);
        try{
            service.getSortedEmployeeList();
            fail();
        }catch (NullPointerException ex){
        }
    }
}