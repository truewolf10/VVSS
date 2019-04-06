package salariati.model;

import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.validator.EmployeeValidator;

public class Employee {

	/** The first name of the employee */
	private String firstName;

	/** The last name of the employee */
	private String lastName;
	
	/** The unique id of the employee */
	private String cnp;
	
	/** The didactic function of the employee inside the university */
	private DidacticFunction function;
	
	/** The salary of the employee */
	private float salary;
	
	/**
	 * Default constructor for employee
	 */
	public Employee() {
		this.firstName = "";
		this.lastName  = "";
		this.cnp       = "";
		this.function  = null;
		this.salary    = 0f;
	}
	
	/**
	 * Constructor with fields for employee
	 */
	public Employee(String firstName, String lastName, String cnp, DidacticFunction function, float salary) {
		this.firstName = firstName;
		this.lastName  = lastName;
		this.cnp       = cnp;
		this.function  = function;
		this.salary    = salary;
	}

	/**
	 * Getter for the employee first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the employee first name
	 *
	 * @param firstName the last name to be set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the employee last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the employee last name
	 * 
	 * @param lastName the last name to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the employee CNP
	 */
	public String getCnp() {
		return cnp;
	}

	/**
	 * Setter for the employee CNP
	 * 
	 * @param cnp the CNP to be set
	 */
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	/**
	 * Getter for the employee didactic function
	 */
	public DidacticFunction getFunction() {
		return function;
	}

	/**
	 * Setter for the employee function
	 * 
	 * @param function the function to be set
	 */
	public void setFunction(DidacticFunction function) {
		this.function = function;
	}

	/**
	 * Getter for the employee salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * Setter for the salary
	 * 
	 * @param salary the salary to be set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	/**
	 * toString function for employee
	 */
	@Override
	public String toString() {
		String employee = "";

		employee += firstName + ";";
		employee += lastName + ";";
		employee += cnp + ";";
		employee += function.toString() + ";";
		employee += salary;
		
		return employee;
	}
	
	/**
	 * equals function for employee
	 */
	public boolean equals(Employee comparableEmployee) {
		boolean hasSameFirsName  = this.firstName.equals(comparableEmployee.getFirstName()),
				hasSameLastName  = this.lastName.equals(comparableEmployee.getLastName()),
				hasSameCNP       = this.cnp.equals(comparableEmployee.getCnp()),
				hasSameFunction  = this.function.equals(comparableEmployee.getFunction()),
				hasSameSalary    = this.salary == comparableEmployee.getSalary();
		return hasSameFirsName && hasSameLastName && hasSameCNP && hasSameFunction && hasSameSalary;
	}
	
	/**
	 * Returns the Employee after parsing the given line
	 * 
	 * @param _employee
	 *            the employee given as String from the input file
	 * @param line
	 *            the current line in the file
	 * 
	 * @return if the given line is valid returns the corresponding Employee
	 * @throws EmployeeException
	 */
	public static Employee getEmployeeFromString(String _employee, int line) throws EmployeeException {
		Employee employee = new Employee();							//c1
		
		String[] attributes = _employee.split("[;]");			//c2
		
		if( attributes.length != 5 ) {									//r3
			throw new EmployeeException("Invalid line at: " + line);	//c4
		} else {
			EmployeeValidator validator = new EmployeeValidator();		//c5
			employee.setFirstName(attributes[0]);						//c6
			employee.setLastName(attributes[1]);						//c7
			employee.setCnp(attributes[2]);								//c8
			
			if(attributes[3].equals("ASISTENT"))						//r9
				employee.setFunction(DidacticFunction.ASISTENT);		//c10
			if(attributes[3].equals("LECTURER"))						//r11
				employee.setFunction(DidacticFunction.LECTURER);		//c12
			if(attributes[3].equals("TEACHER"))							//r13
				employee.setFunction(DidacticFunction.TEACHER);		//c14
			if(attributes[3].equals("CONFERENTIAR"))					//r15
				employee.setFunction(DidacticFunction.CONFERENTIAR);	//c16
			
			employee.setSalary(Float.parseFloat(attributes[4]));		//c17
			
			if( !validator.isValid(employee) ) {						//r18
				throw new EmployeeException("Invalid line at: " + line);//c19
			}
		}
		
		return employee;												//c20
	}
}
