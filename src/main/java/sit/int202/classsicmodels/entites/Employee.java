package sit.int202.classsicmodels.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity

@Table(name = "employees")
public class Employee {
    @Id
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private String reportsTo;
    private String jobTitle;
}
