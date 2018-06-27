package parser;

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

    private static final String DATETIME_XML_PROPERTY = "DateTime";

    private static final String PRODUCTION_ORDERS_XML_PROPERTY = "ProductionOrders";

    private static final String PRODUCTION_ORDER_XML_PROPERTY = "ProductionOrder";

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
        order.setDateTime(parseDateTime(this.element.getChildText(DATETIME_XML_PROPERTY, this.element.getNamespace())));

        Collection<ProductionOrder> productionOrders = new ArrayList<>();

        Element xmlProductionOrders = this.element.getChild(PRODUCTION_ORDERS_XML_PROPERTY, this.element.getNamespace());

        for (Element xmlProductionOrder : xmlProductionOrders.getChildren(PRODUCTION_ORDER_XML_PROPERTY, xmlProductionOrders.getNamespace())) {
            order.addProductionOrder(new ProductionOrderParser(xmlProductionOrder).parse());
        }

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
