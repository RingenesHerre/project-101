package no.acntech.project101.employee;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Employee {
    //TODO Create the enitity for Employee
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;



    public Employee() {
        // Hibernate
    }

    public Employee(String firstName, String lastName, Long companyId, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
