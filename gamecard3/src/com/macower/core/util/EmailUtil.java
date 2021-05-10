package com.macower.core.util;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dominicsayers.isemail.IsEMail;
import com.dominicsayers.isemail.IsEMailResult;
import com.dominicsayers.isemail.dns.DNSLookupException;


public class EmailUtil {
	protected Logger log = LoggerFactory.getLogger(getClass());
	
	private String EMAILHOST = "servicen3dscard@gmail.com";
	private String EMAIL_FROM ="servicen3dscard@gmail.com";
	
	private String PASSWORD ="cmpwn3ds";
	
	private String AUTHENTICATOR = "servicen3dscard@gmail.com" ;
	
	public EmailUtil(String emailHost ,String emailFrom ,String authenticatior , String password ){
		if(emailHost == null || emailFrom == null || authenticatior == null || password == null){
			log.info("使用系统默认邮件设置") ;
		}else{
			this.AUTHENTICATOR = authenticatior ;
			this.PASSWORD = password ;
			this.EMAILHOST = emailHost ;
			this.EMAIL_FROM = emailFrom ;
		}
	}
	
	public EmailUtil(String emailHost , String password ){
		new EmailUtil(emailHost,emailHost,emailHost,password) ;
	}
	
	public boolean sendGmailEmail(String subject, String msg,String emailto) {
		String[] email_to = emailto.split(";");
		if (EMAILHOST == null || EMAIL_FROM == null || email_to == null
				|| msg == null) {
			log.error("error.email.unknown") ;
		}
		HtmlEmail email = new HtmlEmail();  
		email.setTLS(true);
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(25);
		email.setAuthenticator(new DefaultAuthenticator(AUTHENTICATOR, PASSWORD));
		email.setSSL(true);
		//email.setSSLOnConnect(true);
		try {
			for (String str : email_to) {
				str = str.trim() ;
				if(!checkEmail(str)){
					log.error(str + " email is not exists") ;
					return false ;
				}else{
					email.addTo(str);
				}
				
			}
			email.setFrom(EMAIL_FROM);
			email.setSubject(subject);
			email.setCharset("GBK");
			email.setHtmlMsg(msg) ;
			email.send();
			log.info("--- email send ok---") ;
			return true ;
		} catch (EmailException e) {
			log.error(e.getMessage()) ;
		} catch (DNSLookupException e) {
			log.error(e.getMessage()) ;
		}
		return false ;
	}
	
	public static void main(String[] args) {
		EmailUtil util = new EmailUtil(null,null);
		util.sendGmailEmail("www", "pppppppppppp", "1532801929@qq.com") ;
	}
	
	//验证邮箱是否存在
	public boolean checkEmail(String email) throws DNSLookupException {
		  if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
		   return false;
		  }
		  IsEMailResult result = IsEMail.is_email_verbose(email, true);
		  switch (result.getState()) {
		  case OK:
		   return true;
		  default:
		   return false;
		  }
		 }


	public boolean sendQQEmail(String subject, String msg,String emailto){
		String[] email_to = emailto.split(";");
		EMAILHOST  = "1532801929@qq.com" ;
		EMAIL_FROM = "1532801929@qq.com" ;
		if (EMAILHOST == null || EMAIL_FROM == null || email_to == null
				|| msg == null) {
			log.error("error.email.unknown") ;
		}
		//Email email = new SimpleEmail();
		HtmlEmail email = new HtmlEmail();  
		email.setTLS(true);
		//smtp.qq.com
		email.setHostName("smtp.qq.com");  
	    email.setAuthentication("1532801929@qq.com", "weiwei930722");
		 
		email.setSmtpPort(25);
		//email.setAuthenticator(new DefaultAuthenticator(AUTHENTICATOR, PASSWORD));
		email.setSSL(true);
		//email.setSSLOnConnect(true);
		try {
			for (String str : email_to) {
				
				if(!checkEmail(str)){
					log.warn(str + " email is not exists") ;
				}else{
					email.addTo(str);
				}
				
			}
			email.setFrom(EMAIL_FROM);
			email.setSubject(subject);
			email.setCharset("GBK");
			email.setHtmlMsg(msg) ;
			email.send();
			log.info("---email send ok---") ;
			return true ;
		} catch (EmailException e) {
			log.error(e.getMessage()) ;
		} catch (DNSLookupException e) {
			log.error(e.getMessage()) ;
		}
		return false ;

	}

}
