package org.mtgstock.tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class URLTools {
	public static final String HEADER_JSON="application/json";
	public static final String HEADER_HTML="text/html";
	public static final String REFERER = "Referer";
	public static final String HOST = "Host";
	public static final String X_REQUESTED_WITH = "X-Requested-With";
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ACCEPT = "Accept";
	public static final String ORIGIN = "Origin";
	public static final String UPGR_INSECURE_REQ= "Upgrade-Insecure-Requests";
	public static final String USER_AGENT = "User-Agent";
	public static final String CONTENT_TYPE="Content-Type";
	protected static Logger logger = LogManager.getLogger(URLTools.class);

	
	private URLTools()	{}
	
	public static String getExternalIp()
	{
		try {
			return extractAsString("http://checkip.amazonaws.com");
		} catch (IOException e) {
			return "0.0.0.0";
		}
	}
	
	public static String extractAsString(URL url,Charset enc) throws IOException
	{
		HttpURLConnection con = openConnection(url);
		String ret = IOUtils.toString(con.getInputStream(), enc);
		close(con);
		return ret;
	}
	
	public static JsonElement extractJson(String url) throws IOException
	{
		HttpURLConnection con = openConnection(url);
		JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
		JsonElement e= JsonParser.parseReader(reader);
		reader.close();
		close(con);
		return e;
	}
	
	
	public static HttpURLConnection getConnection(URL url,String userAgent) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
			connection.setRequestProperty(USER_AGENT, userAgent);
			connection.setAllowUserInteraction(true);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("GET");
			int status = connection.getResponseCode();
			if (!isCorrectConnection(connection) && (status == HttpURLConnection.HTTP_MOVED_TEMP|| status == HttpURLConnection.HTTP_MOVED_PERM || status == HttpURLConnection.HTTP_SEE_OTHER)) {
				return getConnection(connection.getHeaderField("Location"));
			}
		
		return connection;
	}
	
	
	
	public static HttpURLConnection openConnection(String url) throws IOException {
		return openConnection(new URL(url));
	}
	
	public static HttpURLConnection getConnection(String url) throws IOException {
		return getConnection(new URL(url),MTGStockConstants.USER_AGENT_VALUE);
	}
	
	public static HttpURLConnection openConnection(URL url) throws IOException {
		HttpURLConnection con = getConnection(url,MTGStockConstants.USER_AGENT_VALUE);
		con.connect();
		return con;
	}
	
	public static JsonElement toJson(String s)
	{
		return JsonParser.parseString(s);
	}
	
	public static String extractAsString(String url,Charset enc) throws IOException
	{
		return extractAsString(new URL(url),enc); 
	}
	
	public static String extractAsString(URL url) throws IOException
	{
		return extractAsString(url,MTGStockConstants.DEFAULT_ENCODING); 
	}
	
	public static String extractAsString(String url) throws IOException
	{
		return extractAsString(new URL(url),MTGStockConstants.DEFAULT_ENCODING); 
	}
	
	public static void close(HttpURLConnection con)
	{
		try {
			con.getInputStream().close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	
	public static boolean isCorrectConnection(HttpURLConnection connection) {
			try {
				
				int resp=connection.getResponseCode();
				if(resp >= 200 && resp < 300)
				{
					return true;
				}
				else
				{
					if(connection.getErrorStream()!=null)
					{
						logger.error("Error " + connection.getURL() +": " +  connection.getRequestMethod());
						logger.trace("Error Trace : " + IOUtils.toString(connection.getErrorStream(),MTGStockConstants.DEFAULT_ENCODING));
					}

					return false;
				}
			} catch (IOException e) {
				logger.error(e);
				return false;
			}
	}

	public static String readHeader(String h, String url) throws IOException {
		HttpURLConnection  con =  (HttpURLConnection) new URL(url).openConnection();
		return con.getHeaderField(h);
	}


	
}
