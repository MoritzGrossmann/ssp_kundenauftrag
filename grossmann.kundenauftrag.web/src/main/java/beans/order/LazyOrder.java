package beans.order;

import model.Order;

import java.sql.Date;

public class LazyOrder {

    public LazyOrder(Order order) {
        this.dateTime = order.getDateTime();
        this.id = order.getId();
        this.customerName = String.format("%s %s", order.getCustomer().getFirstname(), order.getCustomer().getLastname());
        this.customerId = order.getCustomer().getId();
    }

    public Date dateTime;

    public int id;

    public String customerName;

    public int customerId;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
