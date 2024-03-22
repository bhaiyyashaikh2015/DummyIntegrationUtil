package com.voicera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class DummyIntegrationUtil2 {
	
	public static void main(String[] args) {
		System.out.println("Inside DummyIntegrationUtil class...");
		
		String integrationName = "searchAccount";
		Map<String, String> query_pairs = new HashMap<>();
		System.out.println();
		String accountDetails;
		try {
			accountDetails = getDetailsForMethodName(integrationName,query_pairs);
			System.out.println("Resut from jar / DummyIntegrationUtil ==> " +accountDetails);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String getDetailsForMethodName(String integrationName, Map<String, String> map2) throws IOException {

		String jsonData = "";
		
		URL obj = new URL("http://localhost:4444/validationSession/demoServlet?methodName=bhaiyya");
		URLConnection conn = obj.openConnection();
		
		// read response body
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;
	    StringBuilder response = new StringBuilder();

	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();

	    System.out.println("jsonData==> " +response.toString());
	    jsonData =response.toString();

		return jsonData;
	}

}
