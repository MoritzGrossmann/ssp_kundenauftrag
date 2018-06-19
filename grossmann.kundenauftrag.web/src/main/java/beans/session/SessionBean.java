package beans.session;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SessionBean{

    private String baseUrl;

    public String getBaseUrl() {
        String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return baseUrl;
    }
}
