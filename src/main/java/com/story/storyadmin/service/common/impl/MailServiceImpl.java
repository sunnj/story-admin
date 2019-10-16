package com.story.storyadmin.service.common.impl;

import com.story.storyadmin.config.props.EmailConfig;
import com.story.storyadmin.service.common.MailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

@Async
@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private EmailConfig emailConfig;
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 发送简单邮件 
	 * @param sendTo 收件人
	 * @param titel 标题
	 * @param content 内容
	 */
	@Override
	public void sendSimpleMail(String sendTo, String titel, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailConfig.getEmailFrom());
		message.setTo(sendTo);
		message.setSubject(titel);
		message.setText(content);
		try{
			mailSender.send(message);
		} catch (MailException e) {
			logger.error("sendSimpleMail error.", e);
		}
	}

	/**
     * 发送简单带附件邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 附件路径
     */
	@Override
	public void sendAttachmentsMail(String sendTo, String titel, String content, List<String> attachments) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(titel);
			helper.setText(content);
			for (String filePath : attachments) {
				FileSystemResource file = new FileSystemResource(new File(filePath));
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
				helper.addAttachment(fileName, file);
			}
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("sendAttachmentsMail error.", e);
		} catch (MailException e) {
			logger.error("sendAttachmentsMail error.", e);
		}
		
	}

	/**
     * 发送内嵌静态资源邮件
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 资源id及路径
     */
	@Override
	public void sendInlineMail(String sendTo, String titel, String content, Map<String,String> attachments) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimeMessage, true);

			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(titel);
			helper.setText(content, true);
			
			for (Map.Entry<String, String> entry : attachments.entrySet()) {
				FileSystemResource file = new FileSystemResource(new File(entry.getValue()));
				helper.addInline(entry.getKey(), file);
			}
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("sendInlineMail error.", e);
		} catch (MailException e) {
			logger.error("sendInlineMail error.", e);
		}
	}

	/**
     * 发送模板邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param context 内容参数
     * @param attachments 附件
     * @param templateName 模板路径
     */
	@Override
	public void sendTemplateMail(String sendTo, String titel, Map<String,Object> context, List<String> attachments,String templateName) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;

		Configuration configuration  = new Configuration(Configuration.VERSION_2_3_28);
		configuration.setClassForTemplateLoading(this.getClass(),"/mail");
		Template template;
		try {
			template = configuration.getTemplate(templateName);
			helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(sendTo);
			helper.setSubject(titel);

			Writer writer=new StringWriter();
			template.process(context,writer);
			helper.setText(writer.toString(), true);

			for (String filePath : attachments) {
				FileSystemResource file = new FileSystemResource(new File(filePath));
				String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
				helper.addAttachment(fileName, file);
			}
			
			mailSender.send(mimeMessage);
		} catch (IOException e) {
			logger.error("sendTemplateMail error.", e);
		} catch (MessagingException e) {
			logger.error("sendTemplateMail error.", e);
		} catch (MailException e) {
			logger.error("sendTemplateMail error.", e);
		} catch (TemplateException e) {
			logger.error("sendTemplateMail error.", e);
		}
	}
}