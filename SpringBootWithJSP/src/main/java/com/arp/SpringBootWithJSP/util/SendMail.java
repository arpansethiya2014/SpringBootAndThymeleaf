package com.arp.SpringBootWithJSP.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;

public class SendMail {

	public static void send(String from, String password, StudentRegistration studentRegistration) {
		String msg = "Your Registration completed please check Your Details.";
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(studentRegistration.getEmail()));
			message.setSubject("SVN : Registration Confirmation Mail");
			// message.setText(msg);
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String table = "<table style=\"width:50%\" border=\"1\"><tr ><th>Name</th><td>"
					+ studentRegistration.getName() + "</td></tr><tr><th>Father Name</th><td>"
					+ studentRegistration.getFatherName() + "</td></tr><tr><th>Mobile</th><td>"
					+ studentRegistration.getMobile() + "</td></tr><tr><th>Course</th><td>"
					+ studentRegistration.getCourse() + "</td></tr><tr><th>Address</th><td>"
					+ studentRegistration.getAddress() + " , " + studentRegistration.getCity()
					+ "</td></tr><tr><th>Gender</th><td>" + studentRegistration.getGender() + "</td></tr></table>";
			String htmlText = "<H2>Dear " + studentRegistration.getName() + ",</H2><H5>" + msg + "</H5><center>" + table
					+ "</center<br><img width=\"70%\" height=\"40%\" src=\"cid:image\">";
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource("D:/projects/1.jpg");

			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID", "<image>");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
			// send message
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
