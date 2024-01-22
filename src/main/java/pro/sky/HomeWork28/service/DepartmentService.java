package pro.sky.HomeWork28.service;

import pro.sky.HomeWork28.model.Employee;

import java.util.List;
import java.util.Map;


public interface DepartmentService {

    List<Employee> printEmployeesDepartment(int department);

    int sumSalaryDepartment(int departmentId);

    Integer minimumSalaryDepartment(int department);

    Integer maximumSalaryDepartment(int department);

    Map<Integer, List<Employee>> printEmployees();
}
