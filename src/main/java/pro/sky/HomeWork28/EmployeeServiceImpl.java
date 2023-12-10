package pro.sky.HomeWork28;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork28.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork28.exception.EmployeeNotFoundException;
import pro.sky.HomeWork28.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final int MAX_EMPLOYEES = 3;

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private String buildKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (employees.size() == MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("");
        }
        String key = buildKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.put(key, new Employee(firstName, lastName, salary, department));
        return null;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        Employee employee = employees.remove(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = buildKey(firstName, lastName);
        Employee employee = employees.get(key);
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Collection<Employee> printEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
