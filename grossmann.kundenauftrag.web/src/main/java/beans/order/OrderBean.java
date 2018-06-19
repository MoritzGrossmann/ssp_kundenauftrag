package beans.order;

import database.OrderRepository;
import model.Order;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class OrderBean implements Serializable {

    @EJB
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        this.orders = orderRepository.getAll();
    }

    public void deleteOrder(Order order) {
        try {
            this.orderRepository.delete(order);
            this.orders.remove(order);
        } catch (Exception e) {

        }
    }

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
