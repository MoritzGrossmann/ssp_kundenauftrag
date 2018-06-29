package beans.session;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Bean zum laden von Session-Spezifischen Daten
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private String baseUrl;

    /**
     * Gibt die Basis-URL der Webanwendung zurück
     * @return
     */
    public String getBaseUrl() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
