package nl.dtl.fairsearchengine.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class HttpURLConnect {

	private final String USER_AGENT = "Mozilla/5.0";

	/*public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}*/

	// HTTP GET request
	public String sendGet(String url) throws Exception {
		

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		//request.addHeader("User-Agent", USER_AGENT);
		request.addHeader("Accept", "text/turtle");

		//In some situations the Stream doesn't close when the size is unknown. Header should be added
		//Check https://stackoverflow.com/questions/1577719/java-sockets-bufferedreader-and-readline-hang/1577833#1577833
		request.addHeader("Connection", "close");

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " +
                       response.getStatusLine().getStatusCode());

		BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
		
		int charRead = 0;
        char[] buffer = new char[1024];
        StringBuffer stringBuffer = new StringBuffer();
        
        while ((charRead = bufferedReader.read(buffer)) > 0) {
            stringBuffer.append(buffer, 0, charRead);
        }
	
		//System.out.println("result" + stringBuffer.toString());
		
		return stringBuffer.toString();
		
	}

	// HTTP POST request
	public void sendPost(String url, String bodyParameters) throws Exception {

	
		//String url = "https://selfsolve.apple.com/wcResults.do";

		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// add header
		//post.setHeader("User-Agent", USER_AGENT);

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
		urlParameters.add(new BasicNameValuePair("cn", ""));
		urlParameters.add(new BasicNameValuePair("locale", ""));
		urlParameters.add(new BasicNameValuePair("caller", ""));
		urlParameters.add(new BasicNameValuePair("num", "12345"));

		
		//new UrlEncodedFormEntity(urlParameters)
		post.setEntity(new StringEntity(bodyParameters));

		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " +
                                    response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
	}

}
