package pro.sky.HomeWork28;

import java.util.Collection;

public interface DepartmentService {

    Employee minimumSalaryDepartment(int department);

    Employee maximumSalaryDepartment(int department);

    Collection<Employee> printEmployeesDepartment(int department);

    Employee printEmployeesByDepartment();
}
