package sit.int202.classsicmodels.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(of = {"officeCode", "city", "country"})

@Entity
@Table(name = "offices")

@NamedQueries({
        @NamedQuery(name = "Office.FIND_BY_CITY_OR_COUNTRY",
                query = "SELECT o " +
                        "FROM Office o " +
                        "WHERE LOWER(o.city) LIKE :city OR LOWER(o.country) LIKE :country")
})

public class Office {

    @Id
    private String officeCode; // <------- This primary key must have @Id

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private String phone;

    private String territory;

    @OneToMany(mappedBy = "officeCode")
    private List<Employee> employeeList;
}
