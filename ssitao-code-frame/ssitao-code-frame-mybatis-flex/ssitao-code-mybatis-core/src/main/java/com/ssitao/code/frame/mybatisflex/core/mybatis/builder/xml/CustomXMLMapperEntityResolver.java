package com.ssitao.code.frame.mybatisflex.core.mybatis.builder.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * @author ssitao
 * @description: TODO
 * @date 2025/7/31 11:12
 */
public class CustomXMLMapperEntityResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
        if (systemId != null && systemId.endsWith(".xml")) {
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new InputSource(new StringReader("<my:select xmlns:my='http://mybatis.org/schema/my'></my:select>")));
                Element root = doc.getDocumentElement();
                root.setAttribute("id", "customSelect");
                root.setAttribute("resultType", "java.util.Map");
                return new InputSource(new StringReader(docToString(doc)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private String docToString(Document doc) {
        try {
            javax.xml.transform.Transformer transformer = javax.xml.transform.TransformerFactory.newInstance().newTransformer();
            javax.xml.transform.stream.StreamResult result = new javax.xml.transform.stream.StreamResult(new java.io.StringWriter());
            javax.xml.transform.dom.DOMSource source = new javax.xml.transform.dom.DOMSource(doc);
            transformer.transform(source, result);
            return result.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
