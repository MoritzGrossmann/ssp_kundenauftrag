import database.CustomerRepository;
import database.Repository;
import model.Customer;
import model.Order;
import model.ProductionOrder;
import xml.CustomerImporter;
import xml.ParseXmlException;

import java.io.File;
import java.util.List;

public class Main {

    private static File xmlFile = new File("grossmann.kundenauftrag.program/src/main/resources/customer.xml");

    public static void main(String[] args) {
        System.out.println("Application is starting...");

        Repository<Customer> customerRepository = new CustomerRepository();

        CustomerImporter customerImporter = new CustomerXmlImporter();

        try{

        List<Customer> customers = customerImporter.readFile(xmlFile);

            for (Customer c : customers) {
                for (Order o : c.getOrders()) {
                    o.setCustomer(c);
                    for(ProductionOrder p : o.getProductionOrders()) {
                        p.setCustomerOrder(o);
                    }
                }
                customerRepository.insert(c);
            }

        } catch (ParseXmlException e) {
            e.printStackTrace();
        }



        System.out.println("Application is stopping...");

    }


}
