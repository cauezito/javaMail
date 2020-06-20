package br.com.cauezito.java_mail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SendEmail {

	private String user = "developer.test9627@gmail.com";
	private String password = "134679caa";
	private String recipients = "";
	private String sender = "";
	private String subject = "";
	private String text = "";
	
	
	public SendEmail(String recipients, String sender,String subject, String textBody) {
		this.recipients = recipients;
		this.sender = sender;
		this.subject = subject;
		this.text = textBody;
		this.configSMTP();
	}

	private Properties configSMTP() {
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); /*Autorização*/
		properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
		properties.put("mail.smtp.host", "smtp.gmail.com"); /*Servidor gmail*/
		properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465"); /*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao smtp*/
		
		return properties;
	}

	public void send() {
		try {
			Session session = Session.getInstance(this.configSMTP(), new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Address[] toUser = InternetAddress.parse(recipients); //add e-mails 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, sender));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(subject);
			message.setContent(text, "text/html; charset=utf-8");
			Transport.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendWithFile() {
		try {
			Session session = Session.getInstance(this.configSMTP(), new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			Address[] toUser = InternetAddress.parse(recipients); //add e-mails 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user, sender));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(subject);
			
			MimeBodyPart bodyEmail = new MimeBodyPart();
			bodyEmail.setContent(text, "text/html; charset=utf8");
			
			MimeBodyPart attachmentEmail = new MimeBodyPart();
			attachmentEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(this.simulatePDF(), "application/pfd")));
			attachmentEmail.setFileName("email.pdf");
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bodyEmail);
			multipart.addBodyPart(attachmentEmail);
			
			message.setContent(multipart);
			Transport.send(message);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private FileInputStream simulatePDF() {				
			try {
				Document document = new Document();
				File file = new File("fileTestEmail.pdf");
				file.createNewFile();
				PdfWriter.getInstance(document, new FileOutputStream(file));
				document.open();
				document.add(new Paragraph("Content"));
				document.close();
				return new FileInputStream(file);
			} catch (IOException | DocumentException e) {
				e.printStackTrace();
				return null;
			}		
	}
}
