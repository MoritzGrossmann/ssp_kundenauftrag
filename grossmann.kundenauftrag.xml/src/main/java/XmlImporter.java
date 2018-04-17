import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlImporter {

    private File file;

    public XmlImporter(File file) {
        this.file = file;
    }

    private org.jdom2.Document getXmlDocument() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(this.file);

        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(document);
    }

}
