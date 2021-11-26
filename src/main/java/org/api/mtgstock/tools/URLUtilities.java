package org.api.mtgstock.tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.api.mtgstock.modele.URLCallInfo;
import org.api.mtgstock.services.URLCallListener;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class URLUtilities {
	private CloseableHttpClient httpclient;
	private HttpClientContext httpContext;
	private BasicCookieStore cookieStore;
	protected Logger logger = LogManager.getLogger(this.getClass());
    private URLCallListener listener;
    
    
	public URLUtilities() {
		httpclient = HttpClients.custom().setUserAgent(MTGStockConstants.USER_AGENT_VALUE).setRedirectStrategy(new LaxRedirectStrategy()).build();
		httpContext = new HttpClientContext();
		cookieStore = new BasicCookieStore();
		httpContext.setCookieStore(cookieStore);
	}
	
	public String extractAndClose(HttpResponse response) throws IOException
	{
		var ret = EntityUtils.toString(response.getEntity());
		EntityUtils.consume(response.getEntity());
		return ret;
	}
	
	public HttpResponse execute(HttpRequestBase req) throws IOException
	{
		return httpclient.execute(req,httpContext);
	}
		
	public HttpResponse doGet(String url) throws IOException
	{
		var callInfo = new URLCallInfo();
		Instant start = Instant.now();
		logger.debug("Parsing url " + url);
		var getReq = new HttpGet(url);
		Instant stop = Instant.now();
		long duration = stop.getEpochSecond()-start.toEpochMilli();
		
		callInfo.setStart(start);
		callInfo.setEnd(stop);
		callInfo.setDuration(duration);
		callInfo.setUrl(url);
		callInfo.setRequest(getReq);
		
		var resp = execute(getReq);
		
		callInfo.setResponse(resp);
	
		if(listener!=null)
			listener.notify(callInfo);
		
		
		return resp ;
	}
	
	
	
	

	public JsonElement extractJson(String url) throws IOException {
		var reader = new JsonReader(new InputStreamReader(doGet(url).getEntity().getContent()));
		JsonElement e= JsonParser.parseReader(reader);
		reader.close();
		return e;
		
		
	}

	public void setCallListener(URLCallListener listener2) {
		this.listener=listener2;
		
	}


}
