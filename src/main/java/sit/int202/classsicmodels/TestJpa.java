package sit.int202.classsicmodels;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import sit.int202.classsicmodels.entites.Employee;
import sit.int202.classsicmodels.entites.Office;
import sit.int202.classsicmodels.repositories.EntityManagerBuilder;
import sit.int202.classsicmodels.repositories.OfficeRepository;

import java.util.List;
import java.util.Scanner;

public class TestJpa {
    //    public static void main(String[] args) {
//        EntityManager em = EntityManagerBuilder.getEntityManager();
//        //----------------Transaction---------------------------
//        em.getTransaction().begin();
//        Office office = em.find(Office.class, "1");
//        office.setCity("XXXXXX");
//        office.setCountry("YYYYYY");
//        em.getTransaction().commit();
//        //------------------------------------------------------
////        System.out.println(office);
//        List<Office> officeList = em.createQuery("SELECT o FROM Office o").getResultList();
//        officeList.forEach(o -> System.out.println(o));
//        em.close();
//    }
    private static final Scanner sc = new Scanner(System.in);
    private static final OfficeRepository repository = new OfficeRepository();

    public static void main(String[] args) {
        int choice = 0;
        do {
            choice = menu();
            switch (choice) {
                case 1:
                    addNewOffice();
                    break;
                case 2:
                    updateOffice();
                    break;
                case 3:
                    deleteOffice();
                    break;
                case 4:
                    searchOffice();
                    break;
                case 5:
                    listAllOffice();
                    break;
            }
        } while (choice > 0);
    }

    private static int menu() {
        System.out.println("\n\n");
        System.out.println("M E N U");
        System.out.println("=================");
        System.out.println("1) Add new Office");
        System.out.println("2) Edit Office");
        System.out.println("3) Delete Office");
        System.out.println("4) Search Office");
        System.out.println("5) List All Office");
        System.out.println("---------------------");
        System.out.println("0) EXIT");
        System.out.println("\n Select your choice: ");
        return sc.nextInt();
    }

    private static void listAllOffice() {
        repository.findAll().forEach(o -> printOffice(o));
    }

    private static void addNewOffice() {
        int x = (int) (Math.random() * 99 + 7);
        Office newOffice = new Office();
        newOffice.setOfficeCode(String.valueOf(x));
        newOffice.setAddressLine1("126 Phacha-Utit, Bangmod");
        newOffice.setAddressLine2("ThungKru");
        newOffice.setCity("Bangkok");
        newOffice.setPostalCode("10140");
        newOffice.setState("");
        newOffice.setCountry("Thailand");
        newOffice.setPhone("+66 2 470 9872");
        newOffice.setTerritory("SE-A");
        if (repository.insert(newOffice)) {
            System.out.println("Inserted new Office ::");
        } else {
            System.out.println("Can't insert new Office ::");
        }
        printOffice(newOffice);
    }

    private static void updateOffice() {
        System.out.println("Enter officeCode to UPDATE: ");
        String officeCode = sc.next();
        Office office = repository.find(officeCode);
        if (office != null) {
            System.out.println(".::Updating Office::.");
            printOffice(office);
            repository.getTransaction().begin();
            System.out.println("Enter new city: ");
            office.setCity(sc.next());
            System.out.println("Enter new country: ");
            office.setCountry(sc.next());
            repository.getTransaction().commit();
            System.out.printf("Office %s has benn update already!", officeCode);
        } else {
            System.out.println("Cannot find ");
        }
    }

    private static void deleteOffice() {
        System.out.println("Enter Office code to DELETE(by code): ");
        String officeCode = sc.next();
        System.out.println(officeCode + ((repository.delete(officeCode) ? "was deleted" : "does not exist!")));
        System.out.println("------");
        System.out.println("Enter Office code to DELETE(by object): ");
        officeCode = sc.next();
        Office office = repository.find(officeCode);
        if (repository.delete(office)) {
            System.out.printf("Office %s was deleted\n", officeCode);
        } else {
            System.out.printf("Office Not Found or Error occurred while delete Office %s\n", officeCode);
        }
    }

    private static void searchOffice() {
        System.out.println("Enter country or city to find: ");
        String cityOfCountry = sc.next();
        List<Office> offices = repository.findByOfficeCityOrCountry(cityOfCountry);
        if (!offices.isEmpty()) {
            System.out.printf("Office search by %s* \n", cityOfCountry);
            System.out.println("------------------");
            offices.forEach(o -> printOffice(o));
        } else {
            System.out.printf("Office search by %s* dose not exist!! \n", cityOfCountry);
        }
    }

    private static void printOffice(Office o) {
        System.out.print("Office Code: " + o.getOfficeCode());
        System.out.print(" City: " + o.getCity());
        System.out.println(" Country: " + o.getCountry());
        System.out.println("----------------------------");
        for (Employee employee : o.getEmployeeList()) {
            System.out.println(employee.getEmployeeNumber() + " " + employee.getFirstName() + " " + employee.getLastName());
        }
        System.out.println();
    }
}
