package parser;

import model.ProductionOrder;
import org.jdom2.Element;
import xml.ParseXmlException;

public class ProductionOrderParser implements XmlParser<ProductionOrder> {

    private Element element;

    @Override
    public ProductionOrder parse() throws ParseXmlException {
        ProductionOrder productionOrder = new ProductionOrder();
        String idString = this.element.getText();
        try {
            productionOrder.setId(Integer.parseInt(idString));
        } catch (NumberFormatException e) {
            throw new ParseXmlException(String.format("cannot parse %s into a Number", idString));
        }
        return productionOrder;
    }

    public ProductionOrderParser(Element element) {
        this.element = element;
    }
}
