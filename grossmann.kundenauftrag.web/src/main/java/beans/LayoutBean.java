package beans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LayoutBean
{
    private String theme = "aristo";

    public String getTheme()
    {
        return theme;
    }
}