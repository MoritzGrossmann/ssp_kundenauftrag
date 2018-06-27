package beans.order;

import database.OrderRepository;
import model.Order;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class OrderBean implements Serializable {

    @EJB
    private OrderRepository orderRepository;

    private LazyDataModel<LazyOrder> lazyModel;

    private LazyOrder selectedOrder;

    public LazyOrder getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(LazyOrder selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    @PostConstruct
    public void init() {
        List<Order> orders = orderRepository.getAll();
        lazyModel = new LazyOrderDataModel(orders.stream().map(LazyOrder::new).collect(Collectors.toList()));
    }

    public LazyDataModel<LazyOrder> getLazyModel() {
        return lazyModel;
    }

    public String onRowSelect(SelectEvent event) {
        LazyOrder order = (LazyOrder)event.getObject();

        return String.format("order-detail.xhtml?id=%s",order.getId());
    }
}
