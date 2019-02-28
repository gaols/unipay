package com.github.gaols.unipay.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class XmlUtils {

    public static Map<String, String> parseXml(String xml) {
        StringReader sr = new StringReader(xml);
        InputSource source = new InputSource(sr);
        return doParseXml(source);
    }

    public static Map<String, String> parseXml(InputStream stream) {
        return doParseXml(new InputSource(stream));
    }

    public static Map<String, String> doParseXml(InputSource source) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            applySafeFeatures(dbf);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(source);
            Element root = document.getDocumentElement();
            Map<String, String> ret = new HashMap<>();
            NodeList children = root.getChildNodes();
            int length = children.getLength();
            for (int i = 0; i < length; i++) {
                Node item = children.item(i);
                if (item.getNodeType() == Node.ELEMENT_NODE) {
                    ret.put(item.getNodeName(), item.getTextContent());
                }
            }
            return ret;
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void applySafeFeatures(DocumentBuilderFactory dbf) throws ParserConfigurationException {
        String FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
        dbf.setFeature(FEATURE, true);

        // If you can't completely disable DTDs, then at least do the following:
        // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-general-entities
        // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-general-entities
        // JDK7+ - http://xml.org/sax/features/external-general-entities
        FEATURE = "http://xml.org/sax/features/external-general-entities";
        dbf.setFeature(FEATURE, false);

        // Xerces 1 - http://xerces.apache.org/xerces-j/features.html#external-parameter-entities
        // Xerces 2 - http://xerces.apache.org/xerces2-j/features.html#external-parameter-entities
        // JDK7+ - http://xml.org/sax/features/external-parameter-entities
        FEATURE = "http://xml.org/sax/features/external-parameter-entities";
        dbf.setFeature(FEATURE, false);

        // Disable external DTDs as well
        FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        dbf.setFeature(FEATURE, false);

        // and these as well, per Timothy Morgan's 2014 paper: "XML Schema, DTD, and Entity Attacks"
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
    }
}
