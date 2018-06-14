import database.CustomerRepository;
import database.Repository;
import model.Customer;
import xml.CustomerImporter;

import java.io.File;

public class Main {

    private static File xmlFile = new File("grossmann.kundenauftrag.program/src/main/resources/customer.xml");

    public static void main(String[] args) {
        System.out.println("Application is starting...");

        Repository<Customer> customerRepository = new CustomerRepository();

        CustomerImporter customerImporter = new CustomerXmlImporter();


        System.out.println("Application is stopping...");

    }


}
