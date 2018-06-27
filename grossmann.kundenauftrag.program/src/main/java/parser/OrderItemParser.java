package parser;

import model.OrderItem;
import org.jdom2.Element;
import xml.ParseXmlException;

public class OrderItemParser implements XmlParser<OrderItem> {

    private static final String PRODUCT_XML_PROPERTY  = "Product";

    private static final String COUNT_XML_PROPERTY = "Count";

    private final Element element;

    @Override
    public OrderItem parse() throws ParseXmlException {
        OrderItem orderItem = new OrderItem();

        String product = this.element.getChildText(PRODUCT_XML_PROPERTY, this.element.getNamespace());
        String count = this.element.getChildText(COUNT_XML_PROPERTY, this.element.getNamespace());

        try {
            orderItem.setProductId(Integer.parseInt(product));
            orderItem.setCount(Integer.parseInt(count));
        } catch (NumberFormatException e) {
            throw new ParseXmlException(String.format("cannot parse %s into a Number", e.getCause()));
        }
        return orderItem;
    }

    public OrderItemParser(Element element) {
        this.element = element;
    }
}
