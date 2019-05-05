package salariati.model;

import org.junit.Test;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class EmployeeTest {

    private static final int line = 2;

    @Test
    public void testGetEmployeeFromString_01(){
        String _employee = "andrei;popica;1970701314011;ASISTENT";
        try{
            Employee.getEmployeeFromString(_employee, line);
            fail();
        }catch (EmployeeException ex){
            assertEquals(ex.getMessage(), "Invalid line at: " + line);
        }
    }

    @Test
    public void testGetEmployeeFromString_02(){
        String _employee = "andrei;popica;1970701314011;nimic;2000";
        try{
            Employee.getEmployeeFromString(_employee, line);
            fail();
        }catch (EmployeeException ex){
            assertEquals(ex.getMessage(), "Invalid line at: " + line);
        }
    }

    @Test
    public void testGetEmployeeFromString_03() throws EmployeeException {
        String _employee = "andrei;popica;1970701314011;ASISTENT;2000";
        Employee employee = Employee.getEmployeeFromString(_employee, line);
        assertEquals(DidacticFunction.ASISTENT, employee.getFunction());
    }

    @Test
    public void testGetEmployeeFromString_04() throws EmployeeException {
        String _employee = "andrei;popica;1970701314011;LECTURER;2000";
        Employee employee = Employee.getEmployeeFromString(_employee, line);
        assertEquals(DidacticFunction.LECTURER, employee.getFunction());
    }

    @Test
    public void testGetEmployeeFromString_05() throws EmployeeException {
        String _employee = "andrei;popica;1970701314011;TEACHER;2000";
        Employee employee = Employee.getEmployeeFromString(_employee, line);
        assertEquals(DidacticFunction.TEACHER, employee.getFunction());
    }

    @Test
    public void testGetEmployeeFromString_06() throws EmployeeException {
        String _employee = "andrei;popica;1970701314011;CONFERENTIAR;2000";
        Employee employee = Employee.getEmployeeFromString(_employee, line);
        assertEquals(DidacticFunction.CONFERENTIAR, employee.getFunction());
    }

}