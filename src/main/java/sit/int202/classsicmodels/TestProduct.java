package sit.int202.classsicmodels;

import sit.int202.classsicmodels.entites.Product;
import sit.int202.classsicmodels.repositories.ProductRepository;

import java.util.List;

public class TestProduct {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        List<Product> productList = productRepository.findAll(1);
        System.out.println("page of 1 of " + productRepository.countAll() / 10);
        for (Product product : productList) {
            System.out.println(product.getProductCode() +
                    " " + product.getProductName() +
                    " - " + product.getProductLine());
        }
    }
}
