package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Stellt die n:m-Beziehung zwischen {@link User} und {@link UserGroup} her
 */
@Entity
@Table(name = "user_usergroup")
public class UserUserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_group_id", referencedColumnName = "user_group_id", nullable = false)
    private UserGroup userGroup;

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    //endregion
}
