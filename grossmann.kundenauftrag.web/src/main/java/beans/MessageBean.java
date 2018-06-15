package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "messageBean")
public class MessageBean {

    public void showInfo(String summery, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summery, detail));
    }

    public void showWarning(String summery, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summery, detail));
    }

    public void showError(String summery, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summery, detail));
    }

    public void showFatal(String summery, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, summery, detail));
    }

    public static MessageBean getCurrentInstance() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return (MessageBean) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "messageBean");
    }
}