package statistic;

import database.OrderRepository;
import model.Order;
import org.joda.time.DateTime;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Kontroller zum Erstellen einer Statistik der Kundenaufträge
 */
@Named
@Stateless
public class OrderController {

    @EJB
    private OrderRepository orderRepository;

    /**
     * Gibt eine Map mit einem {@link Month} als Schlüssel und der Anzahl der Kundenaufträge in diesem Monat als {@link Integer} als Wert zurück
     * @return
     */
    public Map<Month, Integer> getMonthStatistics() {

        Map<Month, Integer> monthStatistics = new TreeMap<>();

        List<Order> orders = orderRepository.getAll();

        orders.forEach(o -> {
            DateTime dateTime = new DateTime(o.getDateTime());

            Month month = new Month(dateTime.getYear(), dateTime.getMonthOfYear());

            if (monthStatistics.containsKey(month)) {
                monthStatistics.put(month, monthStatistics.get(month) + 1);
            } else {
                monthStatistics.put(month, 1);
            }
        });

        return monthStatistics;
    }
}
