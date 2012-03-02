package com.github.mcaserta.versionlounge.xml;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="svnTagsDirUrl" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="svnUser" use="optional" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="svnPass" use="optional" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tagName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "project")
public class Project {

    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String svnTagsDirUrl;
    @XmlAttribute
    protected String svnUser;
    @XmlAttribute
    protected String svnPass;
    @XmlAttribute(required = true)
    protected String tagName;

    /**
     * Gets the value of the svnTagsDirUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvnTagsDirUrl() {
        return svnTagsDirUrl;
    }

    /**
     * Sets the value of the svnTagsDirUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvnTagsDirUrl(String value) {
        this.svnTagsDirUrl = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the svnUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvnUser() {
        return svnUser;
    }

    /**
     * Sets the value of the svnUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvnUser(String value) {
        this.svnUser = value;
    }

    /**
     * Gets the value of the svnPass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvnPass() {
        return svnPass;
    }

    /**
     * Sets the value of the svnPass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvnPass(String value) {
        this.svnPass = value;
    }

    /**
     * Gets the value of the tagName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the value of the tagName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagName(String value) {
        this.tagName = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("svnTagsDirUrl", svnTagsDirUrl).
                append("svnUser", svnUser).
                append("svnPass", svnPass).
                append("tagName", tagName).
                toString();
    }

}
