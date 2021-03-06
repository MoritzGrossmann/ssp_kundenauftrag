package beans.order;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import java.util.*;

public class LazyOrderDataModel extends LazyDataModel<LazyOrder> {

    private final List<LazyOrder> datasource;

    public LazyOrderDataModel(List<LazyOrder> datasource) {
        this.datasource = datasource;
    }

    @Override
    public LazyOrder getRowData(String rowKey) {
        int iRowKey = Integer.parseInt(rowKey);
        for(LazyOrder order : datasource) {
            if(order.getId() == iRowKey) {
                return order;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(LazyOrder order) {
        return order.getId();
    }

    @Override
    public List<LazyOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<LazyOrder> data = new ArrayList<>();

        //filter
        for(LazyOrder order : datasource) {
            boolean match = true;

            if (filters != null) {
                for (String s : filters.keySet()) {
                    try {
                        Object filterValue = filters.get(s);
                        String fieldValue = String.valueOf(order.getClass().getField(s).get(order));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
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
            data.sort(new LazySorter(sortField, sortOrder));
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
