package beans.session;

import database.UserRepository;
import model.User;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.Principal;

public class Authentication implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2525376181355457794L;

    @Inject
    private UserRepository userRepository;

    private User user;

    public User getUser() {
        if(user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if(principal != null) {
                user = userRepository.getByName(principal.getName());
            }
        }
        return user;
    }

    public String logout() {
        ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
        return "home";
    }

    public boolean isInRole(String role) {
        return getUser() != null && user.getGroupName().equals(role);
    }
}