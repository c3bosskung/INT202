package sit.int202.classsicmodels.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@NamedQueries({
        @NamedQuery(name = "FIND_USER",
        query = "SELECT c " +
                "FROM Customer c " +
                "WHERE concat(trim(c.contactFirstName), ' ', trim(c.contactLastName)) = :user_account")
})

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String password;
}
