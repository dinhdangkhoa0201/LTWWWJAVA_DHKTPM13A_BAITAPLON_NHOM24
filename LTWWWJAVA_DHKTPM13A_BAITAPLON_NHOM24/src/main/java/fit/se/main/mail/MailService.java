package fit.se.main.mail;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class MailService {
	Logger logger = LoggerFactory.getLogger(MailService.class);

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendMail(Mail mail) throws MessagingException {
		System.out.println("- Mail : " + mail);
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
		
		Context context = new Context();
		context.setVariables(mail.getModel());
		String html = templateEngine.process("email/verify-code", context);
		
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(html, true);
		
		sender.send(message);
		logger.info("Success send mail");
	}
}
