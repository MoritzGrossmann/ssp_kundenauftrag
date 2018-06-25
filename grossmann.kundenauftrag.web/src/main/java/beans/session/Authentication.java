package beans.session;

import database.UserRepository;
import model.User;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
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

    public void logout() {
        ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInRole(String role) {
        return getUser() != null && user.getUserUserGroups().stream().anyMatch(userUserGroup -> userUserGroup.getUserGroup().getName().equals(role));
    }
}
