package lang;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name="language")
@ApplicationScoped
public class Language implements Serializable{

    private static final long serialVersionUID = 1L;

    private String localeCode;

    private static Map<String,Locale> countries;
    static{
        countries = new LinkedHashMap<>();
        countries.put("English", Locale.ENGLISH); //label, value
        countries.put("German", Locale.GERMAN);
    }

    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Map<String, Locale> getCountries() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public String getCurrentLocale() {
        return currentLocale.getCountry();
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void setCurrentLocale(String country)
    {
        this.currentLocale = countries.get(country);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(countries.get(country));
    }
}