package pro.sky.HomeWork28.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.HomeWork28.service.DepartmentService;
import pro.sky.HomeWork28.model.Employee;

import java.util.List;
import java.util.Map;


@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public void printEmployeesDepartment(@PathVariable("id") int departmentId) {
        departmentService.printEmployeesDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public int sumSalaryDepartment(@PathVariable("id") int departmentId) {
        return departmentService.sumSalaryDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public Integer maximumSalaryDepartment (@PathVariable("id") int departmentId) {
        return departmentService.maximumSalaryDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Integer minimumSalaryDepartment(@PathVariable("id") int departmentId) {
        return departmentService.minimumSalaryDepartment(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> printEmployees() {
        return departmentService.printEmployees();
    }
}

