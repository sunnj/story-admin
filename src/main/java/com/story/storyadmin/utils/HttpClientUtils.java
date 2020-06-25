package com.story.storyadmin.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 5000;
    private static PoolingHttpClientConnectionManager connMgr;
    private static String LINE = System.getProperty("line.separator");//换行相当于\n

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(10);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
//        // 在提交请求之前 测试连接是否可用
//        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * GET 请求
     * @param rURL
     * @return
     */
    public static String get(String rURL) {
        return get(rURL,null,null);
    }

    /**
     * GET 请求
     * @param rURL
     * @return
     */
    public static String get(String rURL,Map<String,String> params) {
        return get(rURL,params,null);
    }

    public static String get(String rURL,Map<String,String> params, String encoding) {
        String result = "";
        String strUrl = rURL;
        try {
            if(params!=null){
                String strParams= getParams(params);
                if(!StringUtils.isEmpty(strParams)) {
                    strUrl = String.format("%s?%s", rURL, strParams);
                }
            }

            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(60000);//将读超时设置为指定的超时，以毫秒为单位。用一个非零值指定在建立到资源的连接后从 Input 流读入时的超时时间。如果在数据可读取之前超时期满，则会引发一个 java.net.SocketTimeoutException。
            con.setDoInput(true);//指示应用程序要从 URL 连接读取数据。
            con.setRequestMethod("GET");//设置请求方式
            if (con.getResponseCode() == 200) {//当请求成功时，接收数据（状态码“200”为成功连接的意思“ok”）
                InputStream is = con.getInputStream();
                if(encoding!=null){
                    result = formatIsToString(is, encoding);
                }else{
                    result = formatIsToString(is);
                }
            }
        } catch (MalformedURLException e) {
            logger.error("GET请求失败",e);
        } catch (IOException e) {
            logger.error("GET请求失败",e);
        } catch (Exception e) {
            logger.error("GET请求失败",e);
        }
        return result.trim();
    }

    /**
     * POST 请求
     * @param postUrl
     * @param params
     * @return
     */
    public static String post(String postUrl, Map<String, String> params) {
        return post(postUrl,params,"utf-8");
    }

    public static String post(String postUrl, Map<String, String> params, String encoding) {
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            URL url = new URL(postUrl);
            URLConnection urlConn = url.openConnection();
            HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("POST");
            OutputStreamWriter wr = new OutputStreamWriter(httpUrlConn.getOutputStream());
            String content = getParams(params, encoding);
            wr.write(content);
            wr.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), encoding));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            wr.close();
            in.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return sb.toString();
    }

    /**
     * 获取参数内容，将参数解析为"name=tom&age=21"的模式
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getParams(Map<String, String> params) throws UnsupportedEncodingException {
        return getParams(params,"utf-8");
    }

    private static String getParams(Map<String, String> params, String encoding) throws UnsupportedEncodingException {
        String content = null;
        Set<Map.Entry<String, String>> set = params.entrySet();//Map.entrySet 方法返回映射的 collection 视图，其中的元素属于此类
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> i : set) {
            //将参数解析为"name=tom&age=21"的模式
            sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue(), encoding)).append("&");
        }
        if (sb.length() > 1) {
            content = sb.substring(0, sb.length() - 1);
        }
        return content;
    }

    public static String formatIsToString(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
            baos.close();
            is.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
        return new String(baos.toByteArray(), "utf-8");
    }

    /**
     * @param is
     * @param encoding
     * @return String 返回类型
     * @throws Exception 参数
     * @throws
     * @Description 格式字符串
     */
    public static String formatIsToString(InputStream is, String encoding) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
            baos.close();
            is.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
        return new String(baos.toByteArray(), encoding);
    }


    /**
     * HttpUrlConnection
     * @param reqUrl
     * @param reqBodyParams
     * @param contentType
     * @param requestMethod
     * @return
     */
    public static String createHttp(String reqUrl, String reqBodyParams, String contentType, String requestMethod) {

        BufferedReader reader = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(reqUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            if (contentType != null) {
                conn.setRequestProperty("Content-type", contentType);
            }
            if (reqBodyParams != null) {
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
                writer.write(reqBodyParams);
                writer.flush();
                writer.close();
            }
            reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");//\r是回车\n是换行
            }
            logger.info("请求链接为：" + reqUrl + "返回的数据为" + sb.toString());
            return sb.toString();

        } catch (IOException e) {
            logger.error("Error connecting to " + reqUrl + ": " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                logger.error("Error connecting to finally" + reqUrl + ": " + e.getMessage());
            }
        }
        return null;
    }

    /**
     * @param requestUrl
     * @param requestMethod
     * @param headerMap     请求头属性，可以为空
     * @param isSsl         当isSSL=true的时候支持Https处理，当isSSL=false的时候http请求
     * @param sslVersion    支持https的版本参数
     * @return String 返回类型
     * @throws
     * @Description 建立http请求链接支持SSL请求
     */
    public static String createHttps(String requestUrl, String requestMethod, Map<String, Object> headerMap,
                                     boolean isSsl, String sslVersion, String bodyParams) {
        HttpsURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        try {
            SSLSocketFactory ssf = null;
            if (isSsl) {
                //这行代码必须要在创建URL对象之前，因为先校验SSL的https请求通过才可以访问http
                ssf = SSLContextSecurity.createIgnoreVerifySSL(sslVersion);
            }
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            URL url = new URL(requestUrl);

            conn = (HttpsURLConnection) url.openConnection();
            if (isSsl) {
                conn.setSSLSocketFactory(ssf);
            }
            conn.setDoOutput(true);//输出
            conn.setDoInput(true);//输入
            conn.setUseCaches(false);//是否支持缓存

            /*设置请求头属性和值 */
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    Object value = headerMap.get(key);
                    //如：conn.addRequestProperty("Authorization","123456");
                    conn.addRequestProperty(key, String.valueOf(value));
                }
            }

            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当设置body请求参数
            if (!StringUtils.isEmpty(bodyParams)) {
                DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
                outStream.write(bodyParams.getBytes("UTF-8"));
                outStream.close();
                outStream.flush();
            }

            if (conn != null && conn.getResponseCode() == 200) {
                // 从输入流读取返回内容
                inputStream = conn.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "UFT-8");
                bufferedReader = new BufferedReader(inputStreamReader);
                String str;
                StringBuffer buffer = new StringBuffer();
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                    buffer.append("\r").append(LINE);
                }
                return buffer.toString();
            } else {
                return "FAIL";
            }
        } catch (ConnectException ce) {
            logger.error("连接超时：{}", ce + "\t请求链接" + requestUrl);
        } catch (Exception e) {
            logger.error("https请求异常：{}", e + "\t请求链接" + requestUrl);
            return "FAIL";//请求系统频繁
        } finally {
            // 释放资源
            try {
                if (conn != null) {
                    conn.disconnect();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                log.error("遇到错误",e);
            }
        }
        return "";
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     * @param @param  apiUrl
     * @param @param  json
     * @param @param  contentType
     * @param @return 参数
     * @return String 返回类型
     * @throws
     */
    public static String httpClientPost(String reqUrl, Object reqBodyParams, String contentType, String encoding, Map<String, Object> headerMap) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(reqUrl);

        /*设置请求头属性和值 */
        if (headerMap != null && !headerMap.isEmpty()) {
            for (String key : headerMap.keySet()) {
                Object value = headerMap.get(key);
                //如： httpPost.addHeader("Content-Type", "application/json");
                httpPost.addHeader(key, String.valueOf(value));
            }
        }

        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            if (reqBodyParams != null) {
                logger.info(getCurrentClassName() + ".httpClientPost方法返回的参数为：" + reqBodyParams.toString());
                StringEntity stringEntity = new StringEntity(reqBodyParams.toString(), (encoding == null || "".equals(encoding) ? "utf-8" : encoding));//解决中文乱码问题
                if (encoding != null && encoding != "") {
                    stringEntity.setContentEncoding(encoding);
                }
                if (contentType != null && contentType != "") {
                    stringEntity.setContentType(contentType);
                }
                httpPost.setEntity(stringEntity);

            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null && response.getStatusLine().getStatusCode() == 200) {
                //Attempted read from closed stream,因为EntityUtils.toString(HttpEntity)方法被使用了多次。所以每个方法内只能使用一次。
                //httpStr = EntityUtils.toString(entity, "UTF-8");
                String buffer = IOUtil.getInputStream(entity.getContent());
                logger.info("HttpUrlPost的entity返回的信息为：" + buffer);
                return buffer;//返回
            } else {
                logger.error("HttpUrlPost的entity对象为空");
                return result;
            }
        } catch (IOException e) {
            logger.error("HttpUrlPost出现异常，异常信息为:" + e.getMessage());
            log.error("遇到错误",e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("response.close失败", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("httpClient.close失败", e);
                }
            }
        }
        return result;
    }


    /**
     * @throws
     * @Description 发送 POST 请求（HTTP），JSON形式
     */
    public static String httpClientPost(String reqUrl, Map<String, Object> reqBodyParams, String contentType, String encoding, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return commonHttpClientPost(reqUrl, reqBodyParams, contentType, encoding, headerMap, httpClient);
    }

    /**
     * @return String 返回类型
     * @throws
     * @Description httpClientGet
     */
    public static String httpClientGet(String reqUrl, Map<String, Object> reqBodyParams, String encoding, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = commonHttpClientGet(reqUrl, reqBodyParams, encoding,
                headerMap, httpClient);
        return httpStr;
    }

    /**
     * @param reqBodyParams
     * @param encoding
     * @param reqParamStr
     * @return String 返回类型
     * @throws IOException
     * @throws UnsupportedEncodingException 参数
     * @Description reqParamStr
     */
    private static String reqParamStr(Map<String, Object> reqBodyParams, String encoding, String reqParamStr) throws IOException,
            UnsupportedEncodingException {
        if (reqBodyParams != null && !reqBodyParams.isEmpty()) {
            List<NameValuePair> params = new ArrayList<>();
            for (Map.Entry<String, Object> entry : reqBodyParams.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                params.add(new BasicNameValuePair(key, val.toString()));
            }
            reqParamStr = EntityUtils.toString(new UrlEncodedFormEntity(params, encoding));
        }
        return reqParamStr;
    }

    /**
     * @return CloseableHttpClient 返回类型
     * @Description 创建httpClient
     */
    public static CloseableHttpClient createHttpClient() {
        SSLContext sslcontext;
        try {
            sslcontext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
        } catch (KeyManagementException e) {
            return null;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyStoreException e) {
            return null;
        }
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,
                new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        return httpclient;
    }

    /**
     * @param reqUrl
     * @param reqBodyParams
     * @param contentType
     * @param encoding
     * @param headerMap
     * @return String 返回类型
     * @Description 创建httpsPost
     */
    public static String createHttpsPost(String reqUrl, Map<String, Object> reqBodyParams, String contentType, String encoding, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = createHttpClient();
        return commonHttpClientPost(reqUrl, reqBodyParams, contentType, encoding, headerMap, httpClient);
    }

    /**
     * @param reqUrl
     * @param reqBodyParams
     * @param contentType
     * @param encoding
     * @param headerMap
     * @param httpClient
     * @return String 返回类型
     * @Description commonHttpClientPost
     */
    private static String commonHttpClientPost(String reqUrl, Map<String, Object> reqBodyParams, String contentType, String encoding, Map<String, Object> headerMap, CloseableHttpClient httpClient) {
        String result = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            String reqParamStr = "";
            /*设置请求头属性和值 */
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    Object value = headerMap.get(key);
                    //如： httpPost.addHeader("Content-Type", "application/json");
                    httpPost.addHeader(key, String.valueOf(value));
                }
            }
            reqParamStr = reqParamStr(reqBodyParams, encoding, reqParamStr);

            httpPost.setConfig(requestConfig);
            if (reqParamStr != null) {
                StringEntity stringEntity = new StringEntity(reqParamStr, (encoding == null || "".equals(encoding) ? "utf-8" : encoding));//解决中文乱码问题
                if (encoding != null && encoding != "") {
                    stringEntity.setContentEncoding(encoding);
                }
                if (contentType != null && contentType != "") {
                    stringEntity.setContentType(contentType);
                }
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null && response.getStatusLine().getStatusCode() == 200) {
                String buffer = IOUtil.getInputStream(entity.getContent());
                logger.info(getCurrentClassName() + "#commonHttpClientPost,***reqUrl***:" + reqUrl + ",***Response content*** : " + buffer.toString());
                return buffer;
            } else {
                logger.error("commonHttpClientPost的entity对象为空");
                return result;
            }
        } catch (IOException e) {
            logger.error("HttpUrlPost出现异常，异常信息为:" + e.getMessage());
            log.error("遇到错误",e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("response.close失败", e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    logger.error("httpClient.close失败", e);
                }
            }
        }
        return result;
    }

    /**
     * httpClientGet
     * @param reqUrl
     * @param reqBodyParams
     * @param encoding
     * @param headerMap
     * @return
     */
    public static String createHttpsGet(String reqUrl, Map<String, Object> reqBodyParams, String encoding, Map<String, Object> headerMap) {
        CloseableHttpClient httpClient = createHttpClient();
        String httpStr = commonHttpClientGet(reqUrl, reqBodyParams, encoding, headerMap, httpClient);
        return httpStr;
    }

    /**
     * @param reqUrl
     * @param reqBodyParams
     * @param encoding
     * @param headerMap
     * @param httpClient
     * @return String 返回类型
     * @Description commonHttpClientGet
     */
    private static String commonHttpClientGet(String reqUrl, Map<String, Object> reqBodyParams, String encoding, Map<String, Object> headerMap, CloseableHttpClient httpClient) {
        String httpStr = null;
        String reqParamStr = "";
        CloseableHttpResponse response = null;
        try {

            reqParamStr = reqParamStr(reqBodyParams, encoding, reqParamStr);

            HttpGet httpGet;
            if (!StringUtils.isEmpty(reqParamStr)) {
                System.out.println(reqUrl + "?" + reqParamStr);
                httpGet = new HttpGet(reqUrl + "?" + reqParamStr);
            } else {
                httpGet = new HttpGet(reqUrl);
            }
            /*设置请求头属性和值 */
            if (headerMap != null && !headerMap.isEmpty()) {
                for (String key : headerMap.keySet()) {
                    Object value = headerMap.get(key);
                    //如： httpPost.addHeader("Content-Type", "application/json");
                    httpGet.addHeader(key, String.valueOf(value));
                }
            }
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null && response.getStatusLine().getStatusCode() == 200) {
                httpStr = EntityUtils.toString(entity, encoding);
                logger.info(getCurrentClassName() + "#commonHttpClientGet,***reqUrl:***" + reqUrl + ",Response content*** : " + httpStr);
            } else {
                httpStr = null;
                logger.error("httpClientGet的entity对象为空");
            }
        } catch (IOException e) {
            logger.error("HttpUrlPost出现异常，异常信息为:" + e.getMessage());
            log.error("遇到错误",e);
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    log.error("遇到错误",e);
                }
            }
        }
        return httpStr;
    }


    public static String createHttpPost(String url, List<BasicNameValuePair> params) {
        try {
            CloseableHttpClient httpClient = createHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(params, DEFAULT_ENCODING));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine() != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String retMsg = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_ENCODING);
                if (!StringUtils.isEmpty(retMsg)) {
                    return retMsg;
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return "";
    }

    public static String getCurrentClassName() {
        return HttpClientUtils.class.getName();
    }
}
