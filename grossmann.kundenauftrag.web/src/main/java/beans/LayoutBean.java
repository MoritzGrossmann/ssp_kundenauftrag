package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LayoutBean implements Serializable
{
    private String theme = "aristo";

    public String getTheme()
    {
        return theme;
    }
}