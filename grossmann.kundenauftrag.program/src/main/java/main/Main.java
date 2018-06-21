package main;

import database.*;
import importer.CustomerXmlImporter;
import model.Customer;
import model.User;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.File;
import java.util.Collection;

public class Main {

    private static File xmlFile = new File("grossmann.kundenauftrag.program/src/main/resources/customer.xml");

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    public static void main(String[] args) {
        System.out.println("Application is starting...");

        EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

        System.out.println("Delete all existing Tables");

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("DROP TABLE machine;");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(e.getMessage());
        }

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery("DROP TABLE sequence;");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Creating Tables");

        Repository<Customer> customerRepository = new CustomerRepository();

        Repository<User> userRepository = new UserRepository();

        UserGroupRepository userGroupRepository = new UserGroupRepository();

        if (customerRepository.getAll().size() ==0) {

            System.out.printf("Reading Xml File %s\n", xmlFile.getAbsolutePath());

            CustomerImporter customerImporter = new CustomerXmlImporter();
            try {
                Collection<Customer> customers = customerImporter.readFile(xmlFile);

                customers.forEach(customerRepository::insert);
            } catch (ParseXmlException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            System.out.println("Seeding users");

            UserMockup userMockup = new UserMockup();


            if (userGroupRepository.getAll().size() < 2) {
                userMockup.getUserGroupes().forEach((key, value) -> {
                    userGroupRepository.insert(value);
                });
            }

            if (userRepository.getAll().size() < 2) {
                User admin = userMockup.getAdminUser();
                admin.addUserGroup(userGroupRepository.getByName("admin"));
                userRepository.insert(admin);

                User user = userMockup.getUserUser();
                user.addUserGroup(userGroupRepository.getByName("user"));
                userRepository.insert(user);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        try {
            System.out.println("Trying to create view with username and groupname");
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(
                    "CREATE VIEW view_user_group AS SELECT u.username AS username, g.name AS groupname FROM user AS u " +
                            "INNER JOIN user_usergroup AS ug ON u.user_id = ug.user_id " +
                            "INNER JOIN usergroup AS g ON ug.user_group_id = g.user_group_id;");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("View already exist");
        }

        System.out.println("Application is stopping...");

    }
}
