package beans.order;

import model.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.*;

public class LazyOrderDataModel extends LazyDataModel<Order> {

    private List<Order> datasource;

    public LazyOrderDataModel(List<Order> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Order getRowData(String rowKey) {
        for(Order order : datasource) {
            if(String.valueOf(order.getId()).equals(rowKey))
                return order;
        }

        return null;
    }

    @Override
    public Object getRowKey(Order order) {
        return order.getId();
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Order> data = new ArrayList<Order>();

        //filter
        for(Order order : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(order.getClass().getField(filterProperty).get(order));

                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        }
                        else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }

            if(match) {
                data.add(order);
            }
        }

        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}
