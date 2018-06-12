package beans;

import database.OrderRepository;
import model.Order;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "customerOrderBean", eager = true)
public class CustomerOrderBean {

    @Inject
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
