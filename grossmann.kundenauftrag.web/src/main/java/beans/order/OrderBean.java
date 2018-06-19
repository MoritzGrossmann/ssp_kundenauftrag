package beans.order;

import database.OrderRepository;
import model.Order;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class OrderBean implements Serializable {

    @EJB
    private OrderRepository orderRepository;

    public void deleteOrder(Order order) {
        try {
            this.orderRepository.delete(order);
            this.orders.remove(order);
        } catch (Exception e) {

        }
    }

    private LazyDataModel<Order> lazyModel;

    private Order selectedOrder;

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyOrderDataModel(orderRepository.getAll());
    }

    public LazyDataModel<Order> getLazyModel() {
        return lazyModel;
    }

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
