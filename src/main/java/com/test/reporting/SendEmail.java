package com.test.reporting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.openqa.selenium.io.Zip;


public class SendEmail {
	
	private static String reportFileName ="Testing Report_"+ new SimpleDateFormat("yyyyMMddHHmmss")
	                                       .format(new Date().getTime());
	
	private static Properties prop = new Properties();
	
	private static void zipReport() throws IOException{
		Zip zip = new Zip();
		zip.zip(new File("target/surefire-reports/html"), new File("ReportZip/TestReport/"+reportFileName+".zip"));
	}
	
	    public static void execute() throws Exception
	    {
	    	
	    	try{
	    	prop.load(new FileInputStream("config.properties"));
	    	String emailTo = prop.getProperty("To");
	    	String emailCC = prop.getProperty("CC");
	    	String emailBCC = prop.getProperty("BCC");
	    	
	    	String emailId = prop.getProperty("emailId");
	    	String password = prop.getProperty("password");
	    	String hostName = prop.getProperty("hostName");
	    	String port = prop.getProperty("port");
	    	
	    	SendEmail.zipReport();
	    	String path = "ReportZip/TestReport/"+reportFileName+".zip";
	    	String[] to = emailTo.trim().split(",");
	    	String[] cc = emailCC.trim().split(",");
	    	String[] bcc = emailBCC.trim().split(",");	

	        SendEmail.sendMail(emailId,
	        	            	password,
	        	            	hostName,
	        	            	port,
	                            "true",
	                            "true",
	                             true, 
	                            javax.net.ssl.SSLSocketFactory.class.getCanonicalName(),
	                            "false",
	                             to,
	                             cc,
	                             bcc,
	                            "Automation Test Report",
	                            "Hi \n Here is your test report of current run that was initiated.",
	                            path,
	                            reportFileName); }catch(Exception e){
	                            	e.printStackTrace();
	                            }
	      }

	      public  static boolean sendMail(
	    		    String userName,
	                String passWord,
	                String host,
	                String port,
	                String starttls,
	                String auth,
	                boolean debug,
	                String socketFactoryClass,
	                String fallback,	             
	                String[] to,
	                String[] cc,
	                String[] bcc,
	                String subject,
	                String text,
	                String attachmentPath,
	                String attachmentName){

	        //Object Instantiation of a properties file.
	        Properties props = new Properties();

	        props.put("mail.smtp.user", userName);

	        props.put("mail.smtp.host", host);

	        if(!"".equals(port)){
	        props.put("mail.smtp.port", port);
	        }

	        if(!"".equals(starttls)){
	            props.put("mail.smtp.starttls.enable",starttls);
	            props.put("mail.smtp.auth", auth);
	        }

	        if(debug){

	        props.put("mail.smtp.debug", "true");

	        }else{

	        props.put("mail.smtp.debug", "false");

	        }

	        if(!"".equals(port)){
	            props.put("mail.smtp.socketFactory.port", port);
	        }
	        if(!"".equals(socketFactoryClass)){
	            props.put("mail.smtp.socketFactory.class",socketFactoryClass);
	        }
	        if(!"".equals(fallback)){
	            props.put("mail.smtp.socketFactory.fallback", fallback);
	        }

	        try{

	            Session session = Session.getDefaultInstance(props, null);

	            session.setDebug(debug);

	            MimeMessage msg = new MimeMessage(session);
                //msg.setText(text);
	            msg.setSubject(subject);
	            
	            Multipart multipart = new MimeMultipart();
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            
	            //Datta
	            messageBodyPart.setText(text);
	            multipart.addBodyPart(messageBodyPart);
	            messageBodyPart = new MimeBodyPart();

	            DataSource source = new FileDataSource(attachmentPath);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            
	            messageBodyPart.setFileName(attachmentName+".zip");
	            multipart.addBodyPart(messageBodyPart);

	            msg.setContent(multipart);
	            msg.setFrom(new InternetAddress(userName));

	            for(int i=0;i<to.length;i++){
	             	if(!to[i].isEmpty()){
	             		msg.addRecipient(Message.RecipientType.TO, new
	            				InternetAddress(to[i]));
	            	} 
	            }

	            for(int i=0;i<cc.length;i++){	            	
	            	if(!cc[i].isEmpty()){
	            		msg.addRecipient(Message.RecipientType.CC, new
		                		InternetAddress(cc[i]));
	            	}
	            }

	            for(int i=0;i<bcc.length;i++){	            	
	            	if(!bcc[i].isEmpty()){
	            		msg.addRecipient(Message.RecipientType.BCC, new
	            				InternetAddress(bcc[i]));
	            	}
	            }

	            msg.saveChanges();

	            Transport transport = session.getTransport("smtp");

	            transport.connect(host, userName, passWord);

	            transport.sendMessage(msg, msg.getAllRecipients());

	            transport.close();

	            return true;

	        } catch (Exception mex){
	            mex.printStackTrace();
	            return false;
	        }
	    }
}
