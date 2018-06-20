package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "usergroup")
public class UserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_group_id")
    private int id;

    @OneToMany(mappedBy = "userGroup", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<UserUserGroup> userUserGroups;

    @Basic
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserUserGroup> getUserUserGroups() {
        return userUserGroups;
    }

    public void setUserUserGroups(Collection<UserUserGroup> userUserGroups) {
        this.userUserGroups = userUserGroups;
    }
}
