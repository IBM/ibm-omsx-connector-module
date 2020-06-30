package org.ibm.sterling.mule.oms.operation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.ibm.sterling.mule.oms.exception.OMSException;


public class InvokeAPI  {

	public String invokeOMSService(String postURL, String json, String auth, String contentType) throws Exception  {
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
             public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            	 java.security.cert.X509Certificate[] emptyArray = new java.security.cert.X509Certificate[1];   
         	 	return emptyArray;
             }
             public void checkClientTrusted(X509Certificate[] certs, String authType) {
             }
             public void checkServerTrusted(X509Certificate[] certs, String authType) {
             }
         }
     };
	String responseToSent = null;
	HttpURLConnection con = null;
	try {

	SSLContext sc = SSLContext.getInstance("SSL");
     sc.init(null, trustAllCerts, new java.security.SecureRandom());
     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

     HostnameVerifier allHostsValid = new HostnameVerifier() {
         public boolean verify(String hostname, SSLSession session) {
             return true;
         }
     };

     HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
     URL url = new URL(postURL);
    
    con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		con.setRequestProperty("Content-Type", contentType);
		if(auth != null) {
			con.setRequestProperty( "Authorization",auth);
		}
		OutputStream os = con.getOutputStream();
		os.write(json.getBytes(Charset.forName("UTF-8")));
		os.flush();
		os.close();
		
			StringBuffer response = new StringBuffer();
			
			if(con.getErrorStream() == null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream(),"UTF-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			} else {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getErrorStream(),"UTF-8"));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				throw new OMSException(response.toString());
			}
			
			responseToSent = response.toString();
			
		} catch (OMSException e) {
			throw e;
		}
			return responseToSent;
		} 
	
}