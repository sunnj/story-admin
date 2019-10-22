package com.story.storyadmin.service.common;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    //收件人邮箱
    private String sendTo = "sunnj87@163.com";
    /**
     * 测试简单邮件
     */
    @Test
    public void sendSimpleMail() {
        String title = "This is a simple mail.";
        String content = "This is the content of the mail.";
        mailService.sendSimpleMail(sendTo, title, content);
    }

    /**
     * 测试带附件邮件
     */
    @Test
    public void sendAttachmentsMail() {
        String title = "This is an email with attachments.";
        String content ="This is the content of the mail.";

        List<String> attachments = new ArrayList<String>();
        attachments.add("E:\\web_pic\\006tNc79ly1fj8sf9847xj31kw11zhdw.jpg");
        attachments.add("E:\\web_pic\\laptop-2590647_1280.jpg");

        mailService.sendAttachmentsMail(sendTo,title,content, attachments);
    }

    /**
     * 测试带静态资源邮件
     */
    @Test
    public void sendInlineMail() {

        String title = "This is an email with some static resources.";
        StringBuilder content = new StringBuilder();
        HashMap<String, String> resMap = new HashMap<>();

        String rsc1Id = "resource1";
        String rsc2Id = "resource2";

        resMap.put(rsc1Id, "E:\\web_pic\\006tNc79ly1fj8sf9847xj31kw11zhdw.jpg");
        resMap.put(rsc2Id, "E:\\web_pic\\laptop-2590647_1280.jpg");

        content.append("<html><head><title>Title</title></head>");
        content.append("<body>");
        content.append("<image src=\'cid:" + rsc1Id + "\' /><br/>");
        content.append("<image src=\'cid:" + rsc2Id + "\' />");
        content.append("</body></html>");
        mailService.sendInlineMail(sendTo, title, content.toString(), resMap);
    }

    /**
     * 测试带附件的模板邮件
     */
    @Test
    public void sendTemplateMail() {
        String title = "This is a template mail with attachments.";
        Map<String,Object> context = new HashMap<>();
        context.put("id", "2091");
        context.put("username", "nj.sun");

        List<String> attachments = new ArrayList<>();
        attachments.add("C:\\Users\\sunnj\\Desktop\\test.txt");
        attachments.add("C:\\Users\\sunnj\\Desktop\\test.rar");

        mailService.sendTemplateMail(sendTo,title,context, attachments,"test.ftl");
    }
}
