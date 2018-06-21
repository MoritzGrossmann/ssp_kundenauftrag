package database;

import model.User;
import model.UserGroup;

import java.util.HashMap;
import java.util.Map;

public class UserMockup {

    private static final String ADMIN_USER_NAME = "admin";

    private static final String ADMIN_USER_PASSWORD = "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918";

    private static final String USER_USER_NAME = "user";

    private static final String USER_USER_PASSWORD = "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb";

    private Map<String, UserGroup> userGroupes = new HashMap<>();

    public UserMockup() {
        seedUserGroups();
    }

    public Map<String, UserGroup> getUserGroupes() {
        return userGroupes;
    }

    private void seedUserGroups() {
        UserGroup admin = new UserGroup();
        admin.setName("admin");
        userGroupes.put("admin", admin);

        UserGroup user = new UserGroup();
        user.setName("user");
        userGroupes.put("user", user);
    }

    public User getAdminUser() {
        return getUser(ADMIN_USER_NAME, ADMIN_USER_PASSWORD);
    }

    private User getUser(String adminUserName, String adminUserPassword) {
        User user = new User();
        user.setUsername(adminUserName);
        user.setPass(adminUserPassword);
        return user;
    }

    public User getUserUser() {
        return getUser(USER_USER_NAME, USER_USER_PASSWORD);
    }
}
