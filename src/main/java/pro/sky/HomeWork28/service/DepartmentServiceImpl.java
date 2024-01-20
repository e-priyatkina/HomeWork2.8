package pro.sky.HomeWork28.service;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork28.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> printEmployeesDepartment(int department) { //вывод списка сотрудников отдела
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public int sumSalaryDepartment(int department) {
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Integer minimumSalaryDepartment (int department) { //минимальная зарплата по отделу
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .min(Integer::compareTo)
                .orElse(null);
    }

    @Override
    public Integer maximumSalaryDepartment(int department) { //максимальная зарплата по отделу
        return employeeService.printEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .max(Integer::compareTo)
                .orElse(null);
    }

    @Override
    public Map<Integer, List<Employee>> printEmployees() {
        return employeeService.printEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
