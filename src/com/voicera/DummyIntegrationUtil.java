package com.voicera;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DummyIntegrationUtil {
	
	public static void main(String[] args) {
		System.out.println("Inside DummyIntegrationUtil class...");
		
		String integrationName = "searchAccount";
		
		String url = "beginningClientAccountNumber=5002656925100000&endingClientAccountNumber=5002656925199999&matchType=C1&dateOfBirth=08/06/1974&zipCode=24104&srcSystem=RMEX&accessMethod=IVR";
		
		Map<String, String> query_pairs = splitQuery(url);
		
		printMap(query_pairs);
		
		String accountDetails = getDetailsForMethodName(integrationName,query_pairs);
		System.out.println("Resut from jar / DummyIntegrationUtil ==> " +accountDetails);
		
	}
	
	
	public static Map<String, String> splitQuery(String queryParams) {

		Map<String, String> query_pairs = new LinkedHashMap<String, String>();

		try {
			String[] pairs = queryParams.split("&");
			for (String pair : pairs) {
				int idx = pair.indexOf("=");
				query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
						URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return query_pairs;
	}
	
	public static void printMap(Map<String, String> map) {
		for (String key : map.keySet()) {
			System.out.println(key + " = " + map.get(key));
		}
	}
	
	
	
	
	public static String getDetailsForMethodName(String integrationName, Map<String, String> map) {
		
		String jsonData = "";
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
//				MediaType mediaType = MediaType.parse("text/plain");
//				RequestBody body = RequestBody.create(mediaType, "");
				Request request = new Request.Builder()
				  .url("http://localhost:4444/validationSession/demoServlet?searchText=searchAccount")
				  .method("GET", null)
				  .build();
				try {
					Response response = client.newCall(request).execute();
					jsonData = response.body().string();
					
					System.out.println("Json data is :: "+jsonData);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
				
				return jsonData;
//		return "{\"accountFound\":true,\"numberOfAccounts\":1,\"matches\":[{\"caseNumber\":\"23281083\",\"companyNumber\":\"01\"}],\"additionalInfo\":[{\"firstName\":\"\",\"lastName\":\"\",\"guardianDOB\":\"\",\"guardianSSN\":\"\",\"fill\":\"\"}]}\r\n"
//				+ "";
	}


}
