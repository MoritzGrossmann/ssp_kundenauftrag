package beans.order;

import model.Order;
import org.primefaces.model.SortOrder;

import java.util.Comparator;

public class LazySorter implements Comparator<Order> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(Order order1, Order order2) {
        try {
            Object value1 = Order.class.getField(this.sortField).get(order1);
            Object value2 = Order.class.getField(this.sortField).get(order2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
