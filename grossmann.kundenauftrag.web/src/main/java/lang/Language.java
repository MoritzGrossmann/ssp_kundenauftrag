package lang;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Bean zum ändern der Sprache
 */
@ManagedBean
@SessionScoped
public class Language implements Serializable{

    private static final long serialVersionUID = 1L;
    private String locale;

    /**
     * Map mit allen verfügbaren Sprachen
     */
    private static final Map<String,Object> countries;
    static {

        countries = new LinkedHashMap<>();
        countries.put("German", Locale.GERMAN);
        countries.put("English", Locale.ENGLISH);
    }

    //region Getters and Setters

    public Map<String, Object> getCountries() {
        return countries;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    //endregion

    /**
     * Event, welches triggert wenn die Sprache geändert wurde
     * @param e
     */
    public void localeChanged(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();

        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)) {
                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale)entry.getValue());
            }
        }
    }
}