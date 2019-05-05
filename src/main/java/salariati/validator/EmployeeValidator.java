package salariati.validator;

import salariati.enumeration.DidacticFunction;
import salariati.model.Employee;

public class EmployeeValidator {
	
	public EmployeeValidator(){}

	public boolean isValid(Employee employee) {
		boolean isLastNameValid  = employee.getLastName().matches("[a-zA-Z]+") && (employee.getLastName().length() > 2);
		boolean isCNPValid       = employee.getCnp().matches("[0-9]+") && (employee.getCnp().length() == 13);
		boolean isFunctionValid  = employee.getFunction() == (DidacticFunction.ASISTENT) ||
								   employee.getFunction() == (DidacticFunction.LECTURER) ||
								   employee.getFunction() == (DidacticFunction.CONFERENTIAR) ||
								   employee.getFunction() == (DidacticFunction.TEACHER);
		boolean isSalaryValid    = employee.getSalary() > 0;
		
		return isLastNameValid && isCNPValid && isFunctionValid && isSalaryValid;
	}

	
}
