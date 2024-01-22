package pro.sky.HomeWork28.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.HomeWork28.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private final Collection<Employee> employees = List.of(
            new Employee("Ivan", "Ivanov", 10000, 1),
            new Employee("Petr", "Petrov", 20000, 2),
            new Employee("Maria", "Sidorova", 15000, 3),
            new Employee("Anna", "Potapenko", 12000, 1)
    );

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    @BeforeEach
    public void beforeEach() {
        when(employeeServiceImpl.printEmployees()).thenReturn(employees);
    }

    @Test
    public void getMaxSalaryPositiveTest() {
        int expected = 12000;

        int actual = departmentServiceImpl.maximumSalaryDepartment(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getMaxSalaryNegativeTest() {
        assertThat(departmentServiceImpl.maximumSalaryDepartment(4)).isNull();
    }

    @Test
    public void getMinSalaryPositiveTest() {
        int expected = 10000;

        int actual = departmentServiceImpl.minimumSalaryDepartment(1);
        assertEquals(expected, actual);
    }

    @Test
    public void getMinSalaryNegativeTest() {
        assertThat(departmentServiceImpl.minimumSalaryDepartment(4)).isNull();
    }

    @Test
    public void sumSalaryDepartmentPositiveTest() {
        int expected = 22000;

        int actual = departmentServiceImpl.sumSalaryDepartment(1);

        assertEquals(expected, actual);
    }

    @Test
    public void printEmployeesDepartmentTest() {
        assertThat(departmentServiceImpl.printEmployeesDepartment(2))
                .containsExactlyInAnyOrder(
                        new Employee("Petr", "Petrov", 20000, 2)
                );
    }

    @Test
    public void printEmployees() {
        assertThat(departmentServiceImpl.printEmployees()).containsExactlyInAnyOrderEntriesOf(
              Map.of(
                    1, List.of(new Employee("Ivan", "Ivanov", 10000, 1), new Employee("Anna", "Potapenko", 12000, 1)),
                    2, List.of(new Employee("Petr", "Petrov", 20000, 2)),
                      3, List.of(new Employee("Maria", "Sidorova", 15000, 3))
              )
        );
    }
}
