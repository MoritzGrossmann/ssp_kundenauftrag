import model.Order;
import org.jdom2.Element;

public class OrderParser implements XmlParser<Order> {

    public OrderParser(Element xmlOrder) {
        this.element = xmlOrder;
    }

    private Element element;

    @Override
    public Order parse() {
        return null;
    }
}
