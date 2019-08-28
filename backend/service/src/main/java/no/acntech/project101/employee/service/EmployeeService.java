package no.acntech.project101.employee.service;

import java.util.List;
import java.util.Optional;

import no.acntech.project101.company.consumer.BrregRestClient;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BrregRestClient brregRestClient;

    public EmployeeService(final EmployeeRepository employeeRepository,
                           final BrregRestClient brregRestClient) {
        this.employeeRepository = employeeRepository;
        this.brregRestClient = brregRestClient;
    }

    public Employee save(final Employee employee) {
        //TODO
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findById(final Long id) {
        //TODO
        return employeeRepository.findById(id);
    }

    public List<Employee> findAll() {
        //TODO
        return employeeRepository.findAll();
    }

    public void delete(final Long id) {
        //TODO
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }
    }
}
