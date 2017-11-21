
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * 
 * 				Type defining the <b>BestOfferDetails</b> container, which consists
 * 				of Best Offer details associated with a listing. The <b>BestOfferEnabled</b>
 * 				field in this container is used by <b>Add</b>/<b>Revise</b>/<b>Relist</b> calls to enable the Best Offer feature on a listing.
 * 				<br/><br/>
 * 				<span class="tablenote"><b>Note: </b>
 * 				Historically, the Best Offer feature has not been available for auction listings, but beginning with Version 1027, scheduled to roll out the first week in August 2017, sellers in the US, UK, and DE sites will be able to offer the Best Offer feature in auction listings. Once this new capability rolls out, the seller can offer Buy It Now or Best Offer in an auction listing, but not both.
 * 				</span>
 * 			
 * 
 * <p>Java class for BestOfferDetailsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BestOfferDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BestOfferCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="BestOfferEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="BestOffer" type="{urn:ebay:apis:eBLBaseComponents}AmountType" minOccurs="0"/>
 *         &lt;element name="BestOfferStatus" type="{urn:ebay:apis:eBLBaseComponents}BestOfferStatusCodeType" minOccurs="0"/>
 *         &lt;element name="BestOfferType" type="{urn:ebay:apis:eBLBaseComponents}BestOfferTypeCodeType" minOccurs="0"/>
 *         &lt;element name="NewBestOffer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BestOfferDetailsType", propOrder = {
    "bestOfferCount",
    "bestOfferEnabled",
    "bestOffer",
    "bestOfferStatus",
    "bestOfferType",
    "newBestOffer",
    "any"
})
public class BestOfferDetailsType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "BestOfferCount")
    protected Integer bestOfferCount;
    @XmlElement(name = "BestOfferEnabled")
    protected Boolean bestOfferEnabled;
    @XmlElement(name = "BestOffer")
    protected AmountType bestOffer;
    @XmlElement(name = "BestOfferStatus")
    protected BestOfferStatusCodeType bestOfferStatus;
    @XmlElement(name = "BestOfferType")
    protected BestOfferTypeCodeType bestOfferType;
    @XmlElement(name = "NewBestOffer")
    protected Boolean newBestOffer;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the bestOfferCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBestOfferCount() {
        return bestOfferCount;
    }

    /**
     * Sets the value of the bestOfferCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBestOfferCount(Integer value) {
        this.bestOfferCount = value;
    }

    /**
     * Gets the value of the bestOfferEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBestOfferEnabled() {
        return bestOfferEnabled;
    }

    /**
     * Sets the value of the bestOfferEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBestOfferEnabled(Boolean value) {
        this.bestOfferEnabled = value;
    }

    /**
     * Gets the value of the bestOffer property.
     * 
     * @return
     *     possible object is
     *     {@link AmountType }
     *     
     */
    public AmountType getBestOffer() {
        return bestOffer;
    }

    /**
     * Sets the value of the bestOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link AmountType }
     *     
     */
    public void setBestOffer(AmountType value) {
        this.bestOffer = value;
    }

    /**
     * Gets the value of the bestOfferStatus property.
     * 
     * @return
     *     possible object is
     *     {@link BestOfferStatusCodeType }
     *     
     */
    public BestOfferStatusCodeType getBestOfferStatus() {
        return bestOfferStatus;
    }

    /**
     * Sets the value of the bestOfferStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BestOfferStatusCodeType }
     *     
     */
    public void setBestOfferStatus(BestOfferStatusCodeType value) {
        this.bestOfferStatus = value;
    }

    /**
     * Gets the value of the bestOfferType property.
     * 
     * @return
     *     possible object is
     *     {@link BestOfferTypeCodeType }
     *     
     */
    public BestOfferTypeCodeType getBestOfferType() {
        return bestOfferType;
    }

    /**
     * Sets the value of the bestOfferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BestOfferTypeCodeType }
     *     
     */
    public void setBestOfferType(BestOfferTypeCodeType value) {
        this.bestOfferType = value;
    }

    /**
     * Gets the value of the newBestOffer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNewBestOffer() {
        return newBestOffer;
    }

    /**
     * Sets the value of the newBestOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNewBestOffer(Boolean value) {
        this.newBestOffer = value;
    }

    /**
     * 
     * 
     * @return
     *     array of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object[] getAny() {
        if (this.any == null) {
            return new Object[ 0 ] ;
        }
        return ((Object[]) this.any.toArray(new Object[this.any.size()] ));
    }

    /**
     * 
     * 
     * @return
     *     one of
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object getAny(int idx) {
        if (this.any == null) {
            throw new IndexOutOfBoundsException();
        }
        return this.any.get(idx);
    }

    public int getAnyLength() {
        if (this.any == null) {
            return  0;
        }
        return this.any.size();
    }

    /**
     * 
     * 
     * @param values
     *     allowed objects are
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public void setAny(Object[] values) {
        this._getAny().clear();
        int len = values.length;
        for (int i = 0; (i<len); i ++) {
            this.any.add(values[i]);
        }
    }

    protected List<Object> _getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return any;
    }

    /**
     * 
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object setAny(int idx, Object value) {
        return this.any.set(idx, value);
    }

}
