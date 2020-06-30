package org.ibm.sterling.mule.oms.config;

import org.ibm.sterling.mule.oms.connection.OMSConnectionProvider;
import org.ibm.sterling.mule.oms.operation.BasicOperations;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.license.RequiresEntitlement;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(BasicOperations.class)
@ConnectionProviders(OMSConnectionProvider.class)
@RequiresEntitlement(name = "IBM Corp")
public class BasicConfiguration {

  @Parameter
  private String url;
  
  @Parameter
  @Optional
  @NullSafe
  protected MultiMap<String, String> headers;
  
  @Parameter
  @Optional
  @NullSafe
  protected MultiMap<String, String> queryParams;
  
  @Parameter
  @Optional
  @NullSafe
  protected MultiMap<String, String> uriParams;
  
  @Parameter
  @Optional
  protected String username;
  
  @Parameter
  @Optional
  protected String password;
  
  @Parameter
  @Optional
  protected String omsTokenUrl;

  
  public MultiMap<String, String> getHeaders() {
    return headers;
  }

  
	public MultiMap<String, String> getQueryParams() {
		return queryParams;
	}


	public String getUrl() {
		return url;
	}


	public MultiMap<String, String> getUriParams() {
		return uriParams;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public String getOmsTokenUrl() {
		return omsTokenUrl;
	}


	
}
