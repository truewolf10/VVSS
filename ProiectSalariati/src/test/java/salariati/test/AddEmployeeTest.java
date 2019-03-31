package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.service.EmployeeService;
import salariati.validator.EmployeeValidator;

import static org.junit.Assert.*;

public class AddEmployeeTest {

	private EmployeeService controller;
	private EmployeeValidator employeeValidator;
	
	@Before
	public void setUp() {
		EmployeeRepositoryInterface employeeRepository = new EmployeeMock();
		controller         = new EmployeeService(employeeRepository);
		employeeValidator  = new EmployeeValidator();
	}

    /**
     * Ec1 = lastName ∈ [a-zA-Z] , lastName.size = 3 => BVA = 3
	 */
	@Test
	public void testAddNewEmployee() {
		Employee newEmployee = new Employee("ValidFirstName","val", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(1, controller.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
	}

	/**
	 * Ec1 = lastName ∈ [a-zA-Z] , lastName.size = 1 => BVA = 1
	 */
	@Test
	public void testAddNewEmployee2() {
		Employee newEmployee = new Employee("ValidFirstName","v", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = lastName ∉ [a-zA-Z] , lastName.size = 5
	 */
	@Test
	public void testAddNewEmployee3() {
		Employee newEmployee = new Employee("ValidFirstName","v123r", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = lastName ∈ [a-zA-Z] , lastName.size = 2 => BVA = 2
	 */
	@Test
	public void testAddNewEmployee4() {
		Employee newEmployee = new Employee("ValidFirstName","vr", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = lastName ∉ [a-zA-Z]
	 */
	@Test
	public void testAddNewEmployee9() {
		Employee newEmployee = new Employee("ValidFirstName",null, "1910509055057", DidacticFunction.ASISTENT, 3000);
		try {
			assertFalse(employeeValidator.isValid(newEmployee));
			controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
			fail();
		}catch (NullPointerException ignored){
			assertEquals(0, controller.getEmployeesList().size());
		}
	}

	/**
	 * Ec1 = cnp ∈ [0-9] , cnp.size = 13 => BVA = 13
	 */
	@Test
	public void testAddNewEmployee5() {
		Employee newEmployee = new Employee("ValidFirstName","vra", "1910509055057", DidacticFunction.ASISTENT, 30000);
		assertTrue(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(1, controller.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
	}

	/**
	 * Ec1 = cnp ∈ [0-9] , cnp.size = 12 => BVA = 12
	 */
	@Test
	public void testAddNewEmployee6() {
		Employee newEmployee = new Employee("ValidFirstName","vra", "191050905505", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = cnp ∉ [0-9] , cnp.size = 12 => BVA = 12
	 */
	@Test
	public void testAddNewEmployee7() {
		Employee newEmployee = new Employee("ValidFirstName","vra", "19105090550A", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = cnp ∈ [0-9] , cnp.size = 14 => BVA = 14
	 */
	@Test
	public void testAddNewEmployee8() {
		Employee newEmployee = new Employee("ValidFirstName","vra", "19105090550574", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
		assertEquals(0, controller.getEmployeesList().size());
	}

	/**
	 * Ec1 = cnp ∉ [0-9]
	 */
	@SuppressWarnings("Duplicates")
	@Test
	public void testAddNewEmployee10() {
		Employee newEmployee = new Employee("ValidFirstName", "vra", null, DidacticFunction.ASISTENT, 3000);
		try {
			assertFalse(employeeValidator.isValid(newEmployee));
			controller.addEmployee(newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getCnp(), newEmployee.getFunction(), newEmployee.getSalary());
			fail();
		} catch (NullPointerException ignored) {
			assertEquals(0, controller.getEmployeesList().size());
		}
	}
}
