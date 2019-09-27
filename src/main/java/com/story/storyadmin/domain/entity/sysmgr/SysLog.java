package com.story.storyadmin.domain.entity.sysmgr;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
//@AllArgsConstructor
@Document(collection="syslog")
public class SysLog {
    @Id
    private String id;
    private String account;
    private String ip;
    @Field("requestmethod")
    private String requestMethod;
    private String url;
    private String uri;
    private String clazz;
    @Field("methodname")
    private String methodName;
    @Field("visittime")
    private Date visitTime;
    @Field("spendtime")
    private Long spendTime;
    private String params;

    public SysLog(){}

    public SysLog(String id,String account,String ip,String requestMethod,String url,String uri
        ,String clazz,String methodName,Date visitTime,Long spendTime,String params){
        this.id=id;
        this.account= account;
        this.ip=ip;
        this.requestMethod=requestMethod;
        this.url=url;
        this.uri=uri;
        this.clazz= clazz;
        this.methodName= methodName;
        this.visitTime=visitTime;
        this.spendTime=spendTime;
        this.params= params;
    }

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    private Date endDate;
}