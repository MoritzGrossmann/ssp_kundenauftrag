package beans;

import database.OrderRepository;
import model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

@ManagedBean
@RequestScoped
public class CustomerOrderBean {

    @EJB
    private
    OrderRepository orderRepository;

    ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
        this.order = orderRepository.getById(this.id);
    }

    private Order order;

    public Order getOrder() {
        return this.order;
    }
}
