package pro.sky.HomeWork28;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee minimumSalaryDepartment (int department) { //минимальная зарплата по отделу
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee maximumSalaryDepartment(int department) { //максимальная зарплата по отделу
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Collection<Employee> printEmployeesDepartment(int department) { //вывод списка сотрудников отдела
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Employee printEmployeesByDepartment() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Сотрудники " + i + "-го отдела");
            return employeeService.findAll().stream()
                    .collect(Collectors.groupingBy(Employee::i);
        }
        return null;
    }
}
