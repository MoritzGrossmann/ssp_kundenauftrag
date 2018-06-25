package webservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class Order {

    public Order(model.Order order) {
        this.id = order.getId();
        this.productionOrders = order.getProductionOrders().stream().map(ProductionOrder::new).collect(Collectors.toList());
        this.dateTime = order.getDateTime();
    }

    private int id;

    private Date dateTime;

    private List<ProductionOrder> productionOrders;

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

    public List<ProductionOrder> getProductionOrders() {
        return productionOrders;
    }

    public void setProductionOrders(List<ProductionOrder> productionOrders) {
        this.productionOrders = productionOrders;
    }
}
