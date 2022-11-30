package sit.int202.classsicmodels;

import at.favre.lib.crypto.bcrypt.BCrypt;
import sit.int202.classsicmodels.entites.Customer;
import sit.int202.classsicmodels.repositories.CustomerRepository;

public class TestBcrypt {
    public static void main(String[] args) {
        CustomerRepository  customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findByName("Jean King");
        System.out.println(customer.getContactFirstName() + ' ' + customer.getPassword());
        BCrypt.Result result = BCrypt.verifyer().verify("1234xxxx".toCharArray(), customer.getPassword());
        System.out.println("Is password Correct?: " + result.verified);

        result =  BCrypt.verifyer().verify("abc123".toCharArray(), customer.getPassword());
        System.out.println("Is password Correct?: " + result.verified);
    }
}
