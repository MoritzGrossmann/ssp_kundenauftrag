package xml;

import model.Customer;

import java.io.File;
import java.util.List;

public interface CustomerImporter {

    List<Customer> readFile(File file) throws ParseXmlException;
}
