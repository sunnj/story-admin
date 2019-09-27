package com.story.storyadmin.service.common;

import java.util.List;
import java.util.Map;

public interface MailService {
	/**
	 * 发送简单邮件 
	 * @param sendTo 收件人
	 * @param titel 标题
	 * @param content 内容
	 */
    void sendSimpleMail(String sendTo, String titel, String content);
    /**
     * 发送简单带附件邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 附件路径
     */
    void sendAttachmentsMail(String sendTo, String titel, String content, List<String> attachments);
    /**
     * 发送内嵌静态资源邮件
     * @param sendTo 收件人
     * @param titel 标题
     * @param content 内容
     * @param attachments 资源id及路径
     */
    void sendInlineMail(String sendTo, String titel, String content, Map<String, String> attachments);
    /**
     * 发送模板邮件 
     * @param sendTo 收件人
     * @param titel 标题
     * @param context 内容参数
     * @param attachments 附件
     * @param templateName 模板路径
     */
    void sendTemplateMail(String sendTo, String titel, Map<String,Object> context, List<String> attachments, String templateName);
}
