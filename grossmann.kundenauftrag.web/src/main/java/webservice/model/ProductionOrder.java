package webservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductionOrder {

    public ProductionOrder(model.ProductionOrder productionOrder) {
        this.id = productionOrder.getId();
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
