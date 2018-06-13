import model.Customer;
import model.Order;
import org.jdom2.Element;
import xml.ParseXmlException;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerParser implements XmlParser<Customer> {

    private static final String NAME_XML_PROPERTY = "Name";

    private static final String ID_XML_PROPERTY = "Id";

    private static final String CUSTOMER_ORDERS_XML_PROPERTY = "CustomerOrders";

    private static final String CUSTOMER_ORDER_XML_PROPERTY = "CustomerOrder";

    private static final String CONTACT_XML_PROPERTY = "Contact";

    private static final String STREET_XML_PROPERTY = "Street";

    private static final String POSTCODE_XML_PROPERTY = "PostCode";

    private static final String HOUSENUMBER_XML_PROPERTY = "HouseNumber";

    private static final String CITY_XML_PROPERTY = "City";

    private static final String TELEFON_PRIVATE_XML_PROPERTY = "Private";

    private static final String TELEFON_MOBILE_XML_PROPERTY = "Mobile";

    private static final String EMAIL_XML_PROPERTY = "Email";

    private static final String FAX_XML_PROPERTY = "Fax";

    private static final String COUNTRY_XML_PROPERTY = "Country";


    public CustomerParser(Element xmlElement) {
        this.element = xmlElement;
    }

    private Element element;

    public Customer parse() throws ParseXmlException {
        Customer customer = new Customer();
        customer.setName(this.element.getChildText(NAME_XML_PROPERTY, this.element.getNamespace()));
        customer.setId(Integer.parseInt(this.element.getChildText(ID_XML_PROPERTY, this.element.getNamespace())));

        Element contact = this.element.getChild(CONTACT_XML_PROPERTY,this.element.getNamespace());

        customer.setStreet(contact.getChildText(STREET_XML_PROPERTY, contact.getNamespace()));
        customer.setHouseNumber(contact.getChildText(HOUSENUMBER_XML_PROPERTY, contact.getNamespace()));
        customer.setPostcode(contact.getChildText(POSTCODE_XML_PROPERTY, contact.getNamespace()));
        customer.setCity(contact.getChildText(CITY_XML_PROPERTY, contact.getNamespace()));
        customer.setEmail(contact.getChildText(EMAIL_XML_PROPERTY, contact.getNamespace()));
        customer.setTelefonPrivate(contact.getChildText(TELEFON_PRIVATE_XML_PROPERTY, contact.getNamespace()));
        customer.setTelefonMobile(contact.getChildText(TELEFON_MOBILE_XML_PROPERTY, contact.getNamespace()));
        customer.setFax(contact.getChildText(FAX_XML_PROPERTY, contact.getNamespace()));
        customer.setCountry(contact.getChildText(COUNTRY_XML_PROPERTY, contact.getNamespace()));

        Collection<Order> orders = new ArrayList<>();

        Element customerOrders = this.element.getChild(CUSTOMER_ORDERS_XML_PROPERTY, this.element.getNamespace());

        for (Element xmlOrder : customerOrders.getChildren(CUSTOMER_ORDER_XML_PROPERTY, customerOrders.getNamespace())) {
            orders.add(new OrderParser(xmlOrder).parse());
        }

        customer.setOrders(orders);

        customer.getOrders().forEach(o -> o.setCustomer(customer));
        return customer;
    }
}
