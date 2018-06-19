package lang;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name="language")
@ApplicationScoped
public class Language implements Serializable{

    private static final long serialVersionUID = 1L;
    private String locale;

    private static Map<String,Object> countries;
    static {

        countries = new LinkedHashMap<String,Object>();
        countries.put("German", Locale.GERMAN);
        countries.put("English", Locale.ENGLISH);
    }

    public Map<String, Object> getCountries() {
        return countries;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    //value change event listener
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