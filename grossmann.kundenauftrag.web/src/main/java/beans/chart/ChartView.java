package beans.chart;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import statistic.Month;
import statistic.OrderController;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

@Named
public class ChartView implements Serializable {

    private LineChartModel dateModel;

    ResourceBundle msgs = ResourceBundle.getBundle("internationalization.language", FacesContext.getCurrentInstance().getViewRoot().getLocale());

    @EJB
    private OrderController orderController;

    @PostConstruct
    public void init() {
        createDateModel();
    }

    public LineChartModel getDateModel() {
        return dateModel;
    }

    private void createDateModel() {
        dateModel = new LineChartModel();
        LineChartSeries orders = new LineChartSeries();
        orders.setLabel(msgs.getString("orders"));

        Map<Month, Integer> monthStatistics = orderController.getMonthStatistics();

        monthStatistics.forEach((key, value) -> {
            orders.set(key.toString(), value);
        });

        dateModel.addSeries(orders);

        dateModel.setTitle(msgs.getString("zoom_for_detail"));
        dateModel.setZoom(true);
        dateModel.setAnimate(true);
        dateModel.getAxis(AxisType.Y).setLabel(msgs.getString("orders"));
        DateAxis axis = new DateAxis(msgs.getString("months"));
        axis.setTickAngle(-50);

        Month[] months = new Month[monthStatistics.size()];
        monthStatistics.keySet().toArray(months);

        axis.setMax(months[months.length-1].next().toString());
        axis.setMin(months[0].previous().toString());

        axis.setTickFormat("%b %Y");

        dateModel.getAxes().put(AxisType.X, axis);

    }
}