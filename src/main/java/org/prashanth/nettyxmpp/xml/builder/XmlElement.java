package org.prashanth.nettyxmpp.xml.builder;

import org.prashanth.nettyxmpp.constants.XmlConstants;

import java.util.*;

/**
 * Created by prashanth on 9/8/16.
 */
public class XmlElement {
    private String elementName;
    private String body;

    private Map<String, XmlElementAttribute> attributes = new HashMap<String, XmlElementAttribute>();
    private List<XmlElement> children = new ArrayList<XmlElement>();

    public XmlElement(){

    }

    public XmlElement(String elementName){
        this.elementName = elementName;
    }


    public void setXmlns(String xmlns) {
        this.attributes.put("xmlns", new XmlElementAttribute("xmlns", xmlns));
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addAttribute(String name, String value){
        XmlElementAttribute attribute = new XmlElementAttribute();
        attribute.setAttributeName(name);
        attribute.setAttributeValue(value);

        this.attributes.put(name, attribute);
    }

    public void addChild(XmlElement element){
        this.children.add(element);
    }

    public String toXml(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(XmlConstants.XML_BEGIN_ELEMENT + this.elementName + XmlConstants.SPACE);

        for (Map.Entry<String, XmlElementAttribute> entry: this.attributes.entrySet()){
            XmlElementAttribute attribute = entry.getValue();
            buffer.append(attribute.getAttributeName() + XmlConstants.XML_EQUALS_ELEMENT
                            + XmlConstants.QUOTE + attribute.getAttributeValue()
                            +XmlConstants.QUOTE + XmlConstants.SPACE);
        }

        buffer.append(XmlConstants.XML_END_ELEMENT);
        buffer.append(this.body == null? "": this.body);

        Iterator<XmlElement> childrenIterator = children.iterator();

        while (childrenIterator.hasNext()){
            buffer.append(childrenIterator.next().toXml());
        }

        buffer.append(XmlConstants.XML_END_ELEMENT_BEGIN + this.elementName + XmlConstants.XML_END_ELEMENT);

        return buffer.toString();
    }

}
