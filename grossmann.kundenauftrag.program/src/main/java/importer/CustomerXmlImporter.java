package importer;

import model.Customer;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import parser.CustomerParser;
import parser.XmlParser;
import xml.CustomerImporter;
import xml.ParseXmlException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerXmlImporter implements CustomerImporter {

    private File file;

    private static final String CUSTOMER_XML_PROPERTY = "Customer";

    private org.jdom2.Document getXmlDocument() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(this.file);

        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(document);
    }

    private Element getRootElement() throws ParseXmlException {
        try {
            return getXmlDocument().getRootElement();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new ParseXmlException(e.getMessage());
        }
    }

    @Override
    public List<Customer> readFile(File file) throws ParseXmlException {
        this.file = file;

        List<Customer> customers = new ArrayList<>();

        Element root = getRootElement();
        for (Element e : root.getChildren(CUSTOMER_XML_PROPERTY, root.getNamespace())) {
            XmlParser<Customer> customerXmlParser = new CustomerParser(e);
            customers.add(customerXmlParser.parse());
        }

        return customers;
    }
}
