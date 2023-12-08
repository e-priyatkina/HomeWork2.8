package pro.sky.HomeWork28;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class EmployeeControllerDepartment {
    private final EmployeeService employeeService;

    public EmployeeControllerDepartment(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public Employee maximumSalaryDepartment (@RequestParam int departmentId) {
        return employeeService.maximumSalaryDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minimumSalaryDepartment(@RequestParam int departmentId) {
        return employeeService.minimumSalaryDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Employee printEmployeesByDepartment() {
        return employeeService.printEmployeesByDepartment();
    }

    @GetMapping("/all")
    public Collection<Employee> printEmployeesDepartment(@RequestParam int departmentId) {
        return employeeService.printEmployeesDepartment(departmentId);
    }
}

