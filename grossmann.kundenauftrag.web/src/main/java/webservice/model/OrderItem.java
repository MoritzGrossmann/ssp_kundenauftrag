package webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderItem {

    public OrderItem(model.OrderItem orderItem) {
        this.id = orderItem.getId();
        this.count = orderItem.getCount();
        this.productId = orderItem.getProductId();
    }

    private int id;

    private int count;

    private int productId;

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    //endregion
}
