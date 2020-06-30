package org.ibm.sterling.mule.oms.connection;

import javax.inject.Inject;

import org.mule.runtime.core.api.el.ExpressionManager;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.proxy.ProxyConfig;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class OMSConnection {


	  private final String fullurlpath;

	  public OMSConnection(String fullurlpath) {
	    this.fullurlpath = fullurlpath;
	  }

	  public String getFullurlpath() {
			return fullurlpath;
		}



	public void invalidate() {
	    // do something to invalidate this connection!
	  }

}
