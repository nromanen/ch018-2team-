package com.ch018.library.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;
/**
 * 
 * @author Edd Arazian
 *
 */

@Service
public class SmsService {

	private final Logger logger = LoggerFactory.getLogger(SmsService.class);
	
	private static final String USER_NAME = "";
	private static final String USER_PASS = "";
	private static final String PHONE = "380911146591";
	private static final String SERVICE_URL = "http://smsc.ru/sys/send.php?login=" + USER_NAME + "&psw=" + USER_PASS
										+ "&phones=" + PHONE + "&cost=3";
	private static final String GET = "GET";
	private static final String USER_AGENT = "Mozilla/5.0";
	
	
	public void sendSms(final String message) {
		
		
		Runnable send = new Runnable() {
				
				 
			
				@Override
				public void run() {
					String msg;
					try {
						msg = UriUtils.encodeQuery(message, "UTF-8");
						URL url = new URL(SERVICE_URL + "&mes=" + msg);
						logger.info("after encode {}", url.toString());
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						connection.setRequestMethod(GET);
						connection.setRequestProperty("User-Agent", USER_AGENT);
						
						try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
							String responeLine;
							while((responeLine = reader.readLine()) != null) {
								logger.info("phone : " + PHONE + " " + responeLine + "\n");
							}
						} catch (Exception e) {
							logger.error("during sms sending {}", e.getMessage());
						}
						
					} catch (Exception e) {
						logger.error("during sms forming {}", e.getMessage());
					}
					
				}
		};
		
		Thread sendThread = new Thread(send);
		sendThread.start();
	}
	
	
}
