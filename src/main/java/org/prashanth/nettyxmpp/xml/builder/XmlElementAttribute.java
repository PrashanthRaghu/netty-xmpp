package org.prashanth.nettyxmpp.xml.builder;

/**
 * Created by prashanth on 9/8/16.
 */
public class XmlElementAttribute {

    public XmlElementAttribute(){

    }

    public XmlElementAttribute(String attributeName, String attributeValue){
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    private String attributeName;
    private String attributeValue;

}
