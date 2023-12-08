package pro.sky.HomeWork28;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);

    Employee removeEmployee(String firstName, String lastName, int salary, int department);

    Employee findEmployee(String firstName, String lastName, int salary, int department);

    Collection<Employee> printEmployees();

    Employee minimumSalaryDepartment(int department);

    Employee maximumSalaryDepartment(int department);

    Collection<Employee> printEmployeesDepartment(int department);

    Employee printEmployeesByDepartment();
}
