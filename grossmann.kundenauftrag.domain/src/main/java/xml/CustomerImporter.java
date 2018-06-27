package xml;

import model.Customer;

import java.io.File;
import java.util.List;

/**
 * Importer, um einer Datendatei eine Liste von Kunden auszulesen
 */
public interface CustomerImporter {

    /**
     * List die Datei file und gibt deren Liste von Kunden zurück
     * @param file Datei, welche ausgelesen werden soll
     * @return
     * @throws ParseXmlException
     */
    List<Customer> readFile(File file) throws ParseXmlException;
}
