package org.ibm.sterling.mule.oms.operation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
//import java.util.Base64;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.ibm.sterling.mule.oms.config.BasicConfiguration;
import org.mule.runtime.extension.api.annotation.license.RequiresEntitlement;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputXmlType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
@RequiresEntitlement(name = "IBM Corp")
public class BasicOperations {

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
 * @throws URISyntaxException 
 * @throws IOException 
 * @throws NoSuchAlgorithmException 
 * @throws KeyManagementException 
   */
  
// Logger logger = LoggerFactory.getLogger(BasicOperations.class);
	  
  @MediaType(MediaType.APPLICATION_JSON)
  @OutputJsonType(schema = "get-orderdetails-response.json")
  @DisplayName("get Order Details")
  public InputStream getOrderDetail(@Config BasicConfiguration configuration, @InputXmlType(schema = "get-orderdetails-request.xsd", qname = "Order") InputStream requestBody) throws Exception {
	  logger.info("Get Order Details Operation called");
	  StringWriter writer = new StringWriter();
      IOUtils.copy(requestBody, writer, StandardCharsets.UTF_8);
      String url = configuration.getUrl();
      String username = configuration.getUsername();
      String password = configuration.getPassword();
      String encoding = Base64.getEncoder().encodeToString((username + ":" +password).getBytes(StandardCharsets.UTF_8));
      String auth = "Basic "+encoding;
      String contenttype = "application/xml";
	  return buildHelloMessage(url,writer.toString(), auth, contenttype);
  }
  
  @MediaType(MediaType.APPLICATION_JSON)
  @OutputJsonType(schema = "get-orderlist-response.json")
  @DisplayName("get Order List")
  public InputStream getOrderList(@Config BasicConfiguration configuration, @InputXmlType(schema = "get-orderlist-request.xsd", qname = "Order") InputStream requestBody) throws Exception {
	  StringWriter writer = new StringWriter();
      IOUtils.copy(requestBody, writer, StandardCharsets.UTF_8);
      String url = configuration.getUrl();
      String username = configuration.getUsername();
      String password = configuration.getPassword();
      String encoding = Base64.getEncoder().encodeToString((username + ":" +password).getBytes(StandardCharsets.UTF_8));
      String auth = "Basic "+encoding;
      String contenttype = "application/xml";
	 return buildHelloMessage(url,writer.toString(), auth, contenttype);
  }
  
  @MediaType(MediaType.APPLICATION_JSON)
  @OutputJsonType(schema = "create-order-response.json")
  @DisplayName("Create Order")
  public InputStream captureOrder(@Config BasicConfiguration configuration,@InputXmlType(schema = "create-order-request.xsd", qname = "Order") InputStream requestBody) throws Exception {
	  StringWriter writer = new StringWriter();
      IOUtils.copy(requestBody, writer, StandardCharsets.UTF_8);
      String url = configuration.getUrl();
      String username = configuration.getUsername();
      String password = configuration.getPassword();
      String encoding = Base64.getEncoder().encodeToString((username + ":" +password).getBytes(StandardCharsets.UTF_8));
      String auth = "Basic "+encoding;
      String contenttype = "application/xml";
      
	 return buildHelloMessage(url,writer.toString(),auth,contenttype);
  }
  
    
  @MediaType(MediaType.APPLICATION_JSON)
  @OutputJsonType(schema = "change-order-response.json")
  @DisplayName("Change Order")
  public InputStream changeOrder(@Config BasicConfiguration configuration,@InputXmlType(schema = "change-order-request.xsd", qname = "Order") InputStream requestBody) throws Exception {
	  StringWriter writer = new StringWriter();
      IOUtils.copy(requestBody, writer, StandardCharsets.UTF_8);
      String url = configuration.getUrl();
      String username = configuration.getUsername();
      String password = configuration.getPassword();
      String encoding = Base64.getEncoder().encodeToString((username + ":" +password).getBytes(StandardCharsets.UTF_8));
      String auth = "Basic "+encoding;
      String contenttype = "application/xml";
      
	 return buildHelloMessage(url,writer.toString(),auth,contenttype);
  }
  
  
  /**
   * Private Methods are not exposed as operations
 * @throws IOException 
 * @throws NoSuchAlgorithmException 
 * @throws KeyManagementException 
   */
  private InputStream buildHelloMessage(String URL, Object requestBody, String auth, String contenttype) throws Exception {
	  InvokeAPI invokeapi = new InvokeAPI();
	  String response = null;
	  response = invokeapi.invokeOMSService(URL,requestBody.toString(), auth, contenttype);
	  InputStream outputStream = new ByteArrayInputStream(response.getBytes("UTF-8"));
	  return outputStream;
  }
  
  
 
  
}
