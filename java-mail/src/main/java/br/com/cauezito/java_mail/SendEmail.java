package br.com.cauezito.java_mail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SendEmail {

	private String user = "";
	private String password = "";
	private String recipients = "";
	private String sender = "";
	private String subject = "";
	private String textBody = "";
	
	
	public SendEmail(String recipients, String sender,String subject, String textBody) {
		this.user = user;
		this.password = password;
		this.recipients = recipients;
		this.sender = sender;
		this.subject = subject;
		this.textBody = textBody;
	}

	public void send() {
		try {
			/*configs SMTP*/
			Properties properties = new Properties();
			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true"); /*Autorização*/
			properties.put("mail.smtp.starttls", "true"); /*Autenticação*/
			properties.put("mail.smtp.host", "smtp.gmail.com"); /*Servidor gmail*/
			properties.put("mail.smtp.port", "465"); /*Porta do servidor*/
			properties.put("mail.smtp.socketFactory.port", "465"); /*Especifica a porta a ser conectada pelo socket*/
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); /*Classe socket de conexão ao smtp*/

			Session session = Session.getInstance(properties, new Authenticator() {
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
			message.setContent(textBody, "text/html; charset=utf-8");
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
