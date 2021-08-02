package com.gregpalacios.geomarker.util;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void enviarMail(MailDTO mailDTO, String pathTemplate) throws MessagingException {
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
		
		Context context = new Context();
		context.setVariables(mailDTO.getModel());
		
		String html = templateEngine.process(pathTemplate, context);
		helper.setTo(mailDTO.getTo());
		helper.setText(html, true);
		helper.setSubject(mailDTO.getSubject());
		helper.setFrom(mailDTO.getFrom());
		
		emailSender.send(message);
	}
}
