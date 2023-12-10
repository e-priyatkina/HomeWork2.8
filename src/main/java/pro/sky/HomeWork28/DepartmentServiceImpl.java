package pro.sky.HomeWork28;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee minimumSalaryDepartment (int department) { //минимальная зарплата по отделу
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee maximumSalaryDepartment(int department) { //максимальная зарплата по отделу
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public void printEmployeesDepartment(int department) { //вывод списка сотрудников отдела
        employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .forEach(employee -> System.out.println(
                        employee.getLastName() + " " + employee.getFirstName() + " " + employee.getSalary()
                ));
    }
}
