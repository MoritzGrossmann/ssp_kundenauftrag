package beans.order;

import org.primefaces.model.SortOrder;

import java.util.Comparator;

/**
 * Klasse zum sortieren der Lazy-Datalist
 */
public class LazySorter implements Comparator<LazyOrder> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    public int compare(LazyOrder order1, LazyOrder order2) {
        try {
            Object value1 = LazyOrder.class.getField(this.sortField).get(order1);
            Object value2 = LazyOrder.class.getField(this.sortField).get(order2);

            int value = ((Comparable)value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}
