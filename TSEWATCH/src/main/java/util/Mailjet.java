package util;

import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;

public class Mailjet {
	public static void SendMail(String Sender, String Receiver, String Content) throws MailjetException, MailjetSocketTimeoutException {
		
		MailjetClient client;
	      MailjetRequest request;
	      MailjetResponse response;
	      client = new MailjetClient("e6002ed333b7294e573ff0238a7d04a5", "dc2ef215d33e36ccb09b1c987fb99121", new ClientOptions("v3.1"));
	      request = new MailjetRequest(Emailv31.resource)
	            .property(Emailv31.MESSAGES, new JSONArray()
	                .put(new JSONObject()
	                    .put(Emailv31.Message.FROM, new JSONObject()
	                        .put("Email", Sender)
	                        .put("Name", "GUO KUNPENG"))
	                    .put(Emailv31.Message.TO, new JSONArray()
	                        .put(new JSONObject()
	                            .put("Email", Receiver)
	                            ))
	                    .put(Emailv31.Message.SUBJECT, "RAPPORT - DIGITAL-LEAGUE")
	                    .put(Emailv31.Message.TEXTPART, "DIGITAL-LEAGUE VOUS PRESENTE.")
	                    .put(Emailv31.Message.INLINEDATTACHMENTS,new JSONArray().put(new JSONObject()
	                    		.put("ContentType", "text/html").put("Filename","rapport.html").put("Base64Content", getEncodedStr(Content))))));
	      response = client.post(request);
	      System.out.println(response.getStatus());
	      if(response.getStatus() == 200) {
	    	  System.out.println("Email Sent");
	    	  
	      }else {
	    	  System.out.println(response.getData());
	      }
	}
	
	public static String getEncodedStr (String str) {
		return com.mailjet.client.Base64.encode(str.getBytes());
	}
	
	public static void main(String[] args) {
		try {
			SendMail(null, null,null);
		} catch (MailjetException e) {
			e.printStackTrace();
		} catch (MailjetSocketTimeoutException e) {
			e.printStackTrace();
		}
	}
		
	
}
