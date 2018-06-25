package beans;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LayoutBean implements Serializable
{
    private String theme = "aristo";

    public String getTheme()
    {
        return theme;
    }
}