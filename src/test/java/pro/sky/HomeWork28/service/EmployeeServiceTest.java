package pro.sky.HomeWork28.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.HomeWork28.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork28.exception.EmployeeNotFoundException;
import pro.sky.HomeWork28.exception.EmployeeStorageIsFullException;
import pro.sky.HomeWork28.model.Employee;

import java.util.ArrayList;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach() {
        Collection<Employee> copy = new ArrayList<>(employeeService.printEmployees());
        for (Employee employee : copy) {
            employeeService.removeEmployee(employee.getFirstName(), employee.getLastName());
        }
    }

    @BeforeEach
    public void beforeEach() {
        employeeService.addEmployee("Ivan", "Ivanov", 10000, 1);
        employeeService.addEmployee("Petr", "Petrov", 20000, 2);
        employeeService.addEmployee("Maria", "Sidorova", 15000, 3);
        employeeService.addEmployee("Anna", "Potapenko", 12000, 1);
    }

    @Test
    void addPositiveTest() {
        Employee expected = new Employee("Oleg", "Sidorov", 30000, 2);

        employeeService.addEmployee("Oleg", "Sidorov", 30000, 2);
        assertThatNoException().isThrownBy(() -> employeeService.findEmployee("Oleg", "Sidorov"));
        assertThat(employeeService.findEmployee("Oleg", "Sidorov")).isEqualTo(expected);
        assertThat(employeeService.printEmployees()
                .contains(expected));
    }

    @Test
    void addNegativeOneTest() {
        for (int i = 0; i < 1; i++) {
            employeeService.addEmployee("Alexey" + i, "Alexeev" + i, 20000, 2);
        }
        assertThat(employeeService.printEmployees()).hasSize(5);

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.addEmployee("Alexey", "Alexeev", 30000, 3));
    }

    @Test
    void addNegativeTwoTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeService.printEmployees()).contains(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee("Ivan", "Ivanov", 10000, 1));
    }

    @Test
    void removePositiveTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeService.printEmployees()).contains(expected);

        employeeService.removeEmployee("Ivan", "Ivanov");

        assertThat(employeeService.printEmployees()).doesNotContain(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Ivan", "Ivanov"));
    }

    @Test
    void removeNegativeTest() {
        Employee expected = new Employee("Andrey", "Andreev", 10000, 1);
        assertThat(employeeService.printEmployees()).doesNotContain(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.removeEmployee("Andrey", "Andreev"));
    }

    @Test
    void findPositiveTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeService.printEmployees()).contains(expected);
        assertThat(employeeService.findEmployee("Ivan", "Ivanov")).isEqualTo(expected);
    }

    @Test
    void findNegativeTest() {
        Employee expected = new Employee("Andrey", "Andreev", 10000, 1);
        assertThat(employeeService.printEmployees()).doesNotContain(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Andrey", "Andreev"));
    }

    @Test
    void printEmployees() {
        assertThat(employeeService.printEmployees()).containsExactlyInAnyOrder(
            new Employee("Ivan", "Ivanov", 10000, 1),
            new Employee("Petr", "Petrov", 20000, 2),
            new Employee("Maria", "Sidorova", 15000, 3),
            new Employee("Anna", "Potapenko", 12000, 1)
        );
    }


}
