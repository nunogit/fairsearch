package nl.dtl.fairsearchengine.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpURLConnect {
    
    public static String GET = "GET";
    public static String POST= "POST";
    public static String HEAD = "HEAD";

    HttpResponse response = null;
    String requestBody = "";

    private final String USER_AGENT = "Mozilla/5.0";

    /*public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}*/

    
    public void setRequestBody(String body){
        this.requestBody = body;
    }
    
    public void setHeaders(){
        
    }
    
    public InputStream openInputStream(String url, String method) throws IOException, Exception{
        HttpClient client = new DefaultHttpClient();
     
        if(method.equals(HttpURLConnect.GET)){
            openGetConnection(url);
        } else
        if(method.equals(HttpURLConnect.POST)){
            openPostConnection(url);
        } else 
        if(method.equals(HttpURLConnect.HEAD)){
            openHeadConnection(url);
        } else {
            throw new Exception(); //TODO exchange for custom exception
        }
        
         return response.getEntity().getContent();
        
    }

    /**
     *
     * @param url
     * @return
     */
    public InputStream getGetStream(String url) throws IOException {
        openGetConnection(url);
        System.out.println(url);
        return response.getEntity().getContent();
    }

    // HTTP GET request
    public String sendGet(String url) throws Exception {

        openGetConnection(url);

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
        HttpClient client = HttpClientBuilder.create().build();
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
        System.out.println("Response Code  : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        System.out.println(result.toString());
    }

    public String getContentType() {
        //if(this.uc!=null)
        Header header = this.response.getFirstHeader("Content-Type");
        return header.getName();
    }

    private void openPostConnection(String url) throws IOException {
        
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setEntity(new StringEntity(this.requestBody));
        //post.setHeaders(headers);

        this.response = client.execute(post);

        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Body : " + this.requestBody);
        System.out.println("Response Code : "+ response.getStatusLine().getStatusCode());
    }
                                                                                                                                                                        
    private void openHeadConnection(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpHead post = new HttpHead(url);

        this.response = client.execute(post);

        System.out.println("\nSending 'HEAD' request to URL : " + url);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());//To change body of generated methods, choose Tools | Templates.
    }
    
    private void openGetConnection(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        
        request.addHeader("Connection", "close");

        this.response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());
    }

    //public void setRequestBody(String requestBody) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

   // public void setRequestBody(String requestBody) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

}
