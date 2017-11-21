
package com.ebay.soap.eBLBaseComponents;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				This is the base request type for the <b>GetClientAlertsAuthToken</b> call. This call retrieves a Client Alerts token for the user, which is required when the user makes a GetUserAlerts call (Client Alerts API). a botblock token and URLs for an image or audio clip that the user is to match. <br/><br/> This call does not have any call-specific input parameters Retrieves a token required for the GetUserAlerts call in the Client Alerts API.
 * 			
 * 
 * <p>Java class for GetClientAlertsAuthTokenRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetClientAlertsAuthTokenRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:ebay:apis:eBLBaseComponents}AbstractRequestType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetClientAlertsAuthTokenRequestType")
public class GetClientAlertsAuthTokenRequestType
    extends AbstractRequestType
    implements Serializable
{

    private final static long serialVersionUID = 12343L;

}
