package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LayoutBean
{
    private String theme = "aristo";

    public String getTheme()
    {
        return theme;
    }
}