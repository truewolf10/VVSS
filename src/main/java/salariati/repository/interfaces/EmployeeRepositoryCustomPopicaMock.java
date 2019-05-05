package salariati.repository.interfaces;

import salariati.model.Employee;

import java.util.List;

public class EmployeeRepositoryCustomPopicaMock implements EmployeeRepositoryInterface {

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {

    }

    @Override
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {

    }

    @Override
    public Employee getEmployeeByName(String name) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByCriteria(String criteria) {
        return null;
    }
}
