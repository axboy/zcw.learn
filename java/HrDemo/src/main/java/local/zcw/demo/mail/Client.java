package local.zcw.demo.mail;

import com.sun.mail.util.MailSSLSocketFactory;

import java.security.GeneralSecurityException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/2/2 16:58
 */
public class Client {

    public static void main(String[] args) throws GeneralSecurityException {
        // 收件人电子邮箱
        String to = "zcw1994@live.com";
        // 发件人电子邮箱
        String from = "12345677@qq.com";
        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器
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
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("12345677@qq.com", "123456789"); //发件人邮件用户名、密码
            }
        });

        for (int i = 0; i < 2; i++) {

            try {
                // 创建默认的 MimeMessage 对象
                MimeMessage message = new MimeMessage(session);
                // Set From: 头部头字段
                message.setFrom(new InternetAddress(from));
                // Set To: 头部头字段
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                // Set Subject: 头部头字段
                message.setSubject("This is the Subject Line..." + i);
                // 设置消息体
                message.setText("This is actual message");
                // 发送消息
                Transport.send(message);
                System.out.println("Sent message successfully....from runoob.com");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        }
    }
}
