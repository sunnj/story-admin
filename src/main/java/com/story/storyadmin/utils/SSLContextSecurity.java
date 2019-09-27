package com.story.storyadmin.utils;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

/**
 * 上下文安全处理类
 */
public class SSLContextSecurity {
    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLSocketFactory createIgnoreVerifySSL(String sslVersion) throws NoSuchAlgorithmException, KeyManagementException {
        //SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        SSLContext sc = SSLContext.getInstance(sslVersion); //"TLSv1.2"
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }
            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, new SecureRandom());

        /***
         * 如果 hostname in certificate didn't match的话就给一个默认的主机验证
         */
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        return  sc.getSocketFactory();
    }

    /**
     * @Description 根据版本号进行获取协议项下
     * @Author      liangjilong
     * @Date        2017年6月6日 上午11:15:27
     * @param tslVerision
     * @return 参数
     * @return String[] 返回类型
     * @throws
     */
    public static String[] getProtocols(String tslVerision){
        try {
            SSLContext context = SSLContext.getInstance(tslVerision);
            context.init(null, null, null);
            SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket();
            String [] protocols = socket.getSupportedProtocols();
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(" " + protocols[i]);
            }
            return socket.getEnabledProtocols();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
