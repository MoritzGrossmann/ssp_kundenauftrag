package parser;

import xml.ParseXmlException;

public interface XmlParser<T> {

    /**
     * Konvertiert ein XmlElement in ein Objekt der Klasse T
     * @return Objekt der Klasse T
     * @throws ParseXmlException
     */
    T parse() throws ParseXmlException;
}
