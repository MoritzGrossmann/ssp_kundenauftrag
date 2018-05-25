import model.Order;
import model.ProductionOrder;
import org.jdom2.Element;
import xml.ParseXmlException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class OrderParser implements XmlParser<Order> {

    public OrderParser(Element xmlOrder) {
        this.element = xmlOrder;
    }

    private Element element;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Order parse() throws ParseXmlException {
        Order order = new Order();
        String xmlId = this.element.getChildText("Id", this.element.getNamespace());

        order.setId(parseInt(xmlId));
        order.setDateTime(parseDateTime(this.element.getChildText("DateTime", this.element.getNamespace())));

        Collection<ProductionOrder> productionOrders = new ArrayList<>();

        Element xmlProductionOrders = this.element.getChild("ProductionOrders", this.element.getNamespace());

        for (Element xmlProductionOrder : xmlProductionOrders.getChildren("ProductionOrder", xmlProductionOrders.getNamespace())) {
            productionOrders.add(new ProductionOrderParser(xmlProductionOrder).parse());
        }

        order.setProductionOrders(productionOrders);
        return order;
    }

    private Date parseDateTime(String xmlDateTime) throws ParseXmlException {
        try {
            return new Date(dateFormatter.parse(xmlDateTime.replace('T', ' ')).getTime());
        } catch (ParseException e) {
            throw new ParseXmlException(String.format("Cannot parse %s into a DateTime", xmlDateTime));
        }
    }

    private int parseInt(String xmlInt) throws ParseXmlException {
        try {
            return Integer.parseInt(xmlInt);
        } catch (NumberFormatException e) {
            throw new ParseXmlException(String.format("Cannot parse %s into a Number", xmlInt));
        }
    }
}
