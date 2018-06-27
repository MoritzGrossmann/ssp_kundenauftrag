package main;

import database.*;
import importer.CustomerXmlImporter;
import model.Customer;
import model.User;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.File;
import java.util.Collection;

public class Main {

    private static final String PERSISTENCE_UNIT = "customOrderDataPersistence";

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Please specify a data file");
            return;
        }

        File dataFile = new File(args[0]);

        if (!dataFile.exists()) {
            System.err.println("Datafile don't exist");
            return;
        }

        DatabaseCreator databaseCreator = new DatabaseCreator();

        System.out.println("Create Database customerorder");

        databaseCreator.createDatabase();

        System.out.println("Create User customer");

        databaseCreator.createUser();

        System.out.println("Set privileges of User customer for Database customerOrder");

        databaseCreator.setUserPrivileges();

        EntityManager entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();

        System.out.println("Creating Tables");

        Repository<Customer> customerRepository = new CustomerRepository();

        Repository<User> userRepository = new UserRepository();

        UserGroupRepository userGroupRepository = new UserGroupRepository();

        if (customerRepository.getAll().size() ==0) {

            System.out.printf("Reading Xml File %s\n", dataFile.getAbsolutePath());

            CustomerImporter customerImporter = new CustomerXmlImporter();
            try {
                Collection<Customer> customers = customerImporter.readFile(dataFile);
                System.out.printf("%d customer found\n", customers.size());
                customers.forEach(customerRepository::insert);
            } catch (ParseXmlException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            UserMockup userMockup = new UserMockup();

            System.out.println("Creating Usergroups");

            if (userGroupRepository.getAll().size() < 2) {
                userMockup.getUserGroupes().forEach((key, value) -> {
                    userGroupRepository.insert(value);
                });
            } else {
                System.err.println("Usergroups already exist");
            }

            System.out.println("Creating Users");

            if (userRepository.getAll().size() < 2) {
                User admin = userMockup.getAdminUser();
                admin.addUserGroup(userGroupRepository.getByName("admin"));
                userRepository.insert(admin);

                User user = userMockup.getUserUser();
                user.addUserGroup(userGroupRepository.getByName("user"));
                userRepository.insert(user);
            } else {
                System.err.println("Users already exist");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        try {
            System.out.println("Create view with username and groupname");
            entityManager.getTransaction().begin();
            Query query = entityManager.createNativeQuery(
                    "CREATE VIEW view_user_group AS SELECT u.username AS username, g.name AS groupname FROM user AS u " +
                            "INNER JOIN user_usergroup AS ug ON u.user_id = ug.user_id " +
                            "INNER JOIN usergroup AS g ON ug.user_group_id = g.user_group_id;");
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.err.println("View already exist");
        }

        entityManager.getTransaction().begin();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
