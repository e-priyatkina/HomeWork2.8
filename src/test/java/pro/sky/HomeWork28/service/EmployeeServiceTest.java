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

    private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @AfterEach
    public void afterEach() {
        Collection<Employee> copy = new ArrayList<>(employeeServiceImpl.printEmployees());
        for (Employee employee : copy) {
            employeeServiceImpl.removeEmployee(employee.getFirstName(), employee.getLastName());
        }
    }

    @BeforeEach
    public void beforeEach() {
        employeeServiceImpl.addEmployee("Ivan", "Ivanov", 10000, 1);
        employeeServiceImpl.addEmployee("Petr", "Petrov", 20000, 2);
        employeeServiceImpl.addEmployee("Maria", "Sidorova", 15000, 3);
        employeeServiceImpl.addEmployee("Anna", "Potapenko", 12000, 1);
    }

    @Test
    void addPositiveTest() {
        Employee expected = new Employee("Oleg", "Sidorov", 30000, 2);

        employeeServiceImpl.addEmployee("Oleg", "Sidorov", 30000, 2);
        assertThatNoException().isThrownBy(() -> employeeServiceImpl.findEmployee("Oleg", "Sidorov"));
        assertThat(employeeServiceImpl.findEmployee("Oleg", "Sidorov")).isEqualTo(expected);
        assertThat(employeeServiceImpl.printEmployees()
                .contains(expected));
    }

    @Test
    void addNegativeOneTest() {
        for (int i = 0; i < 1; i++) {
            employeeServiceImpl.addEmployee("Alexey" + i, "Alexeev" + i, 20000, 2);
        }
        assertThat(employeeServiceImpl.printEmployees()).hasSize(5);

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeServiceImpl.addEmployee("Alexey", "Alexeev", 30000, 3));
    }

    @Test
    void addNegativeTwoTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeServiceImpl.printEmployees()).contains(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeServiceImpl.addEmployee("Ivan", "Ivanov", 10000, 1));
    }

    @Test
    void removePositiveTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeServiceImpl.printEmployees()).contains(expected);

        employeeServiceImpl.removeEmployee("Ivan", "Ivanov");

        assertThat(employeeServiceImpl.printEmployees()).doesNotContain(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeServiceImpl.findEmployee("Ivan", "Ivanov"));
    }

    @Test
    void removeNegativeTest() {
        Employee expected = new Employee("Andrey", "Andreev", 10000, 1);
        assertThat(employeeServiceImpl.printEmployees()).doesNotContain(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeServiceImpl.removeEmployee("Andrey", "Andreev"));
    }

    @Test
    void findPositiveTest() {
        Employee expected = new Employee("Ivan", "Ivanov", 10000, 1);
        assertThat(employeeServiceImpl.printEmployees()).contains(expected);
        assertThat(employeeServiceImpl.findEmployee("Ivan", "Ivanov")).isEqualTo(expected);
    }

    @Test
    void findNegativeTest() {
        Employee expected = new Employee("Andrey", "Andreev", 10000, 1);
        assertThat(employeeServiceImpl.printEmployees()).doesNotContain(expected);

        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeServiceImpl.findEmployee("Andrey", "Andreev"));
    }

    @Test
    void printEmployees() {
        assertThat(employeeServiceImpl.printEmployees()).containsExactlyInAnyOrder(
            new Employee("Ivan", "Ivanov", 10000, 1),
            new Employee("Petr", "Petrov", 20000, 2),
            new Employee("Maria", "Sidorova", 15000, 3),
            new Employee("Anna", "Potapenko", 12000, 1)
        );
    }


}
