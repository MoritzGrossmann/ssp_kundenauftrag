package beans.session;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Bean zum laden von Session-Spezifischen Daten
 */
@Named
@SessionScoped
public class SessionBean{

    private String baseUrl;

    /**
     * Gibt die Basis-URL der Webanwendung zurück
     * @return
     */
    public String getBaseUrl() {
        String baseUrl = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        return baseUrl;
    }
}
