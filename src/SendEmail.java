import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

	public static void main(String[] args) {
		/*final String username = "continentalrooms@gmail.com";
		final String password = "cr@12345";
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.starttls.enable", "true");*/
		
		final String username = "bookings@continentalrooms.com";
		final String password = "Admin@123";
		final String to = "sampath@oganro.com";
		Properties prop = new Properties();
		//String smtpHost = "smtpout.europe.secureserver.net";
		String smtpHost = "188.121.53.3";
		String port = "25";
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", smtpHost);
		prop.put("mail.smtp.port", port);
		//prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			String htmlBody = "<strong>This is an HTML Message</strong>";
			String textBody = "This is a Text Message - "+Calendar.getInstance().getTime();
			try {
				textBody += " From: "+InetAddress.getLocalHost().getHostName();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Testing Email");
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);
			message.setText(htmlBody);
			message.setContent(textBody, "text/html");
			
			System.out.println("Sending via "+smtpHost+"|"+port+" ...");
			System.out.println("From: "+username);
			System.out.println("To: "+to);
			
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}