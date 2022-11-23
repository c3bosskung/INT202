package sit.int202.classsicmodels.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

@NamedQueries({
        @NamedQuery(name = "Product.FindAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.Count", query = "SELECT COUNT(p) as count FROM Product p")
})

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String productCode;

    private String productName;

    private String productLine;

    private String productScale;

    private String productVendor;

    private String productDescription;

    @Column(name = "quantityInStock")
    private Integer quantity;

    private BigDecimal buyPrice;

    @Column(name = "MSRP")
    private BigDecimal msrp;
}
