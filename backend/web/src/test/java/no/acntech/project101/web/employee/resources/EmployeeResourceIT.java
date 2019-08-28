package no.acntech.project101.web.employee.resources;

import no.acntech.project101.Project101Application;
import no.acntech.project101.company.Company;
import no.acntech.project101.company.service.CompanyService;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.TestUtil;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Project101Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeResourceIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompanyService companyService;


    @Test
    void findAll() {
        //TODO: implement
        final Employee arnald = new Employee("Arnald","Weber", LocalDate.of(1990,3,31));
        final Employee chris = new Employee("Chris","Paul", LocalDate.of(1985,5,6));
        employeeService.save(arnald);
        employeeService.save(chris);

        ResponseEntity<EmployeeDto[]> response = testRestTemplate.exchange(
                TestUtil.createURL(port,"/employees"),
                HttpMethod.GET,
                new HttpEntity<>(null,new HttpHeaders()),
                EmployeeDto[].class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<EmployeeDto> employees = Arrays.asList(response.getBody());
        assertThat(employees).isNotEmpty()
                .extracting(EmployeeDto::getFirstName,EmployeeDto::getLastName,EmployeeDto::getCompanyId,EmployeeDto::getCompanyId)
                .contains(
                        Tuple.tuple(arnald.getFirstName(),arnald.getLastName(),arnald.getDateOfBirth(),arnald.getCompany()),
                        Tuple.tuple(chris.getFirstName(),chris.getLastName(),chris.getDateOfBirth(),chris.getCompany())
                );

    }

    @Test
    void findById() {
        final Company acme = companyService.save(new Company("ACME", "123456789"));

        final Employee ken = new Employee("Ken", "Guru", LocalDate.of(1994, 10, 1));
        ken.setCompany(acme);
        final Employee savedKen = employeeService.save(ken);


        ResponseEntity<EmployeeDto> response = testRestTemplate.exchange(
                TestUtil.createURL(port, "/employees/" + savedKen.getId()),
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                EmployeeDto.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        EmployeeDto employeeDto = response.getBody();


        assertThat(employeeDto.getDateOfBirth()).isEqualTo(ken.getDateOfBirth());
        assertThat(employeeDto.getFirstName()).isEqualTo(ken.getFirstName());
        assertThat(employeeDto.getLastName()).isEqualTo(ken.getLastName());
    }

    @Test
    void createEmployee() {
        //TODO: implement
        final Employee employee = new Employee("EmployeeFirstName", "EmployeeLastName",LocalDate.of(1990,9,10));
        HttpEntity<Employee> entity = new HttpEntity<>(employee,new HttpHeaders());

        ResponseEntity response = testRestTemplate.exchange(
                TestUtil.createURL(port,"/employees/"),
                HttpMethod.POST,
                entity,
                ResponseEntity.class
        );
    }

    @Test
    void deleteEmployee() {
        final Company acme = companyService.save(new Company("ACME", "123456789"));
        final Employee ken = new Employee("Ken", "Guru", LocalDate.of(1994, 10, 1));
        ken.setCompany(acme);
        final Employee savedKen = employeeService.save(ken);

        ResponseEntity response = testRestTemplate.exchange(
                TestUtil.createURL(port, "/employees/" + savedKen.getId()),
                HttpMethod.DELETE,
                new HttpEntity<>(null, new HttpHeaders()),
                ResponseEntity.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
}
