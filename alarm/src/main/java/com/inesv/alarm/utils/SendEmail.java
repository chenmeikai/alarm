/**
 * 
 */
package com.inesv.alarm.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author meikai
 * @version 2017年9月1日 下午8:21:54
 * 验证邮件
 */
public class SendEmail {
	
	
	/**
	 * 告警邮件
	 * @param host
	 * @param sender
	 * @param password
	 * @param receiver
	 * @param content
	 * @throws Exception
	 */
	public static void sendAlarmEmail(String host, String sender,String password,String receiver,String content) throws Exception {
		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties,new Authenticator(){
			public PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(sender,password ); //发件人邮件用户名、密码
			}
		});
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(sender));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// Set Subject: 头部头字段
			message.setSubject("平台监控告警");
			
			//邮件内容
			String context = "<div>平台有最新的告警信息！</did><div>请及时跟进与处理告警情况，告警内容如下：</div> <br>"
					         + "<p>"+content+"</p>";

			// 设置消息体
			message.setContent(context,"text/html;charset=UTF-8");

			// 发送消息
			Transport.send(message);
			System.out.println("发送告警邮件成功....");
		
	}
	
		
	public static void main(String[] args) {
		
		try {
			String host="smtp.qq.com";
			String sender="562899487@qq.com";
			String password="gmepxwqvhqqbbbhc";
			String receiver="chen_meikai@163.com";
			sendAlarmEmail(host,sender,password,receiver,"hello");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
