package webservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class Order {

    public Order(model.Order order) {
        this.id = order.getId();
        this.orderItems = order.getOrderItems().stream().map(OrderItem::new).collect(Collectors.toList());
        this.dateTime = order.getDateTime();
    }

    private int id;

    private Date dateTime;

    private List<OrderItem> orderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
