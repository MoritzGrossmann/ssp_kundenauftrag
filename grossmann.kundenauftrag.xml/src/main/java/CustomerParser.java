import model.Customer;
import model.Order;
import org.jdom2.Element;
import xml.ParseXmlException;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerParser implements XmlParser<Customer> {

    public CustomerParser(Element xmlElement) {
        this.element = xmlElement;
    }

    private Element element;

    public Customer parse() throws ParseXmlException {
        Customer customer = new Customer();
        customer.setName(this.element.getChildText("Name", this.element.getNamespace()));
        customer.setId(Integer.parseInt(this.element.getChildText("Id", this.element.getNamespace())));
        Collection<Order> orders = new ArrayList<>();

        Element customerOrders = this.element.getChild("CustomOrders", this.element.getNamespace());

        for (Element xmlOrder : customerOrders.getChildren("CustomOrder", customerOrders.getNamespace())) {
            orders.add(new OrderParser(xmlOrder).parse());
        }

        customer.setOrders(orders);
        return customer;
    }
}
