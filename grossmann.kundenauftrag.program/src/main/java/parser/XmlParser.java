package parser;

import xml.ParseXmlException;

public interface XmlParser<T> {

    T parse() throws ParseXmlException;
}
