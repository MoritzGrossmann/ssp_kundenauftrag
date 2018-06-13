package beans;

import database.OrderRepository;
import model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "customerOrderBean", eager = true)
public class CustomerOrderBean {

    @EJB
    private
    OrderRepository orderRepository;

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Order order;

    public Order getOrder() {
        if (this.order == null || this.id != this.order.getId())
            this.order = orderRepository.getById(this.id);
        return this.order;
    }
}
