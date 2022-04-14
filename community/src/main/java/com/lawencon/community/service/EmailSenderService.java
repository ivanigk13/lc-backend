package com.lawencon.community.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lawencon.community.dto.email.EmailTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSenderService {

	private final JavaMailSender javaMailSender;
	private final freemarker.template.Configuration freeMarkerConfiguration;
	
	public void sendMessage(String template, EmailTemplate mail) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setSubject(mail.getSubject());
			mimeMessageHelper.setFrom(mail.getFrom());
			mimeMessageHelper.setTo(mail.getTo());
			mail.setContent(getContentFromTemplate(template, mail.getModel()));
			mimeMessageHelper.setText(mail.getContent(), true);
			
			javaMailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private String getContentFromTemplate(String template, Map<String, Object> model) {
		StringBuffer content = new StringBuffer();
		
		try {
			content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfiguration.getTemplate(template), model));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}
}
