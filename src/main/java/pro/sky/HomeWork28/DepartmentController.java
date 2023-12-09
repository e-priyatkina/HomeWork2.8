package pro.sky.HomeWork28;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    public DepartmentController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maximumSalaryDepartment (@RequestParam int departmentId) {
        return departmentService.maximumSalaryDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee minimumSalaryDepartment(@RequestParam int departmentId) {
        return departmentService.minimumSalaryDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Employee printEmployeesByDepartment() {
        return departmentService.printEmployeesByDepartment();
    }

    @GetMapping("/all")
    public Collection<Employee> printEmployeesDepartment(@RequestParam int departmentId) {
        return departmentService.printEmployeesDepartment(departmentId);
    }
}

