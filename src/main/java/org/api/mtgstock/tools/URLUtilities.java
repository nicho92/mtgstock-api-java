package org.api.mtgstock.tools;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class URLUtilities {
	public static final String USER_AGENT = "User-Agent";
	protected static Logger logger = LogManager.getLogger(URLUtilities.class);

	
	private URLUtilities()	{}
	
	
	
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

}
