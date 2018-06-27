package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {

    private static final String URL = "jdbc:mysql://localhost";

    private static final String ROOT_USER = "root";

    private static final String ROOT_PASSWORD = "";

    private static final String CUSTOMER_USER = "customer";

    private static final String CUSTOMER_PASSWORD = "customer";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String DATABASE_NAME = "customerorder";

    private Connection getConnection() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName(JDBC_DRIVER).newInstance();
        return DriverManager.getConnection(URL, ROOT_USER, ROOT_PASSWORD);
    }

    public void createDatabase() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("CREATE DATABASE IF NOT EXISTS %s", DATABASE_NAME);
            statement.execute(sql);
            connection.close();
        } catch (SQLException | IllegalAccessException | InstantiationException |ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void createUser() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("CREATE USER %s@%s identified by '%s'", CUSTOMER_USER, "localhost", CUSTOMER_PASSWORD);
            statement.execute(sql);
            connection.close();
        } catch (SQLException e){
            System.err.printf("User %s already exist\n", CUSTOMER_USER);
        } catch (IllegalAccessException | InstantiationException  | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setUserPrivileges() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = String.format("GRANT ALL PRIVILEGES on customerorder.* to '%s'@'%s' identified by '%s'", CUSTOMER_USER, "localhost", CUSTOMER_PASSWORD);
            statement.execute(sql);
            connection.close();
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
