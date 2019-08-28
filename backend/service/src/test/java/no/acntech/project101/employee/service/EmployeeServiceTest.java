package no.acntech.project101.employee.service;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void save() {
        //TODO: implement
        final Employee employee = new Employee("Bernard","Lowe",LocalDate.of(1990,4,1));
        when(employeeRepository.save(employee)).thenReturn(employee);

        final Employee savedEmployee = employeeService.save(employee);

        assertThat(savedEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(savedEmployee.getCompany()).isEqualTo(employee.getCompany());
        assertThat(savedEmployee.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
    }

    @Test
    void findById() {
        final Employee employee = new Employee("Ken", "Guru", LocalDate.of(1994, 10, 1));

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        final Employee foundEmployee = employeeService.findById(1L).get();

        assertThat(foundEmployee.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(foundEmployee.getLastName()).isEqualTo(employee.getLastName());
        assertThat(foundEmployee.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
    }

    @Test
    void findAll() {
        //TODO: implement
        final Employee arnald = new Employee("Arnald","Weber",LocalDate.of(1990,3,31));
        final Employee chris = new Employee("Chris","Paul",LocalDate.of(1985,5,6));
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(arnald,chris));

        List<Employee> employees = employeeService.findAll();

        assertThat(employees).hasSize(2);
        assertThat(employees).contains(arnald,chris);
    }

    @Test
    void deleteExisting() {
        //TODO: implement
        when(employeeRepository.existsById(1L)).thenReturn(true);

        employeeService.delete(1L);

        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteNonExisting() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        employeeService.delete(1L);

        verify(employeeRepository).existsById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }
}