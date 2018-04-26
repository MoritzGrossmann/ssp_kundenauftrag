import model.Customer;
import model.Order;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerParser implements XmlParser<Customer> {

    public CustomerParser(Element xmlElement) {
        this.element = xmlElement;
    }

    private Element element;


    public Customer parse() {
        Customer customer = new Customer();
        customer.setId(Integer.parseInt(this.element.getChildText("Id", this.element.getNamespace())));
        customer.setName(this.element.getChildText("Name", this.element.getNamespace()));
        Collection<Order> orders = new ArrayList<>();
        this.element.getChildren("CustomOrders", this.element.getNamespace()).forEach(xmlOrder -> {
            orders.add(new OrderParser(xmlOrder).parse());
        });
        return customer;
    }
}
