package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Repräsentiert eine Benutzer-Entität
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -557220040253974289L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "pass")
    private String pass;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<UserUserGroup> userUserGroups = new ArrayList<>();

    //region Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Collection<UserUserGroup> getUserUserGroups() {
        return userUserGroups;
    }

    public void setUserUserGroups(Collection<UserUserGroup> userUserGroups) {
        this.userUserGroups = userUserGroups;
    }

    //endregion

    /**
     * Fügt dem Benutzer eine Benutzergruppe hinzu und fügt der Benutzergruppe den Benutzer hinzu (n:m)
     * @param userGroup
     */
    public void addUserGroup(UserGroup userGroup) {
        UserUserGroup userUserGroup = new UserUserGroup();
        userUserGroup.setUser(this);
        userUserGroup.setUserGroup(userGroup);
        userGroup.getUserUserGroups().add(userUserGroup);
        this.userUserGroups.add(userUserGroup);
    }
}
