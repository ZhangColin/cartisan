//package com.cartisan.util;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import static org.apache.http.entity.ContentType.TEXT_PLAIN;
//
//public class HttpClientUtil {
//    // utf-8字符编码
//    public static final String CHARSET_UTF_8 = "utf-8";
//
//    // HTTP内容类型。
//    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
//
//    // HTTP内容类型。相当于form表单的形式，提交数据
//    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
//
//    // HTTP内容类型。相当于form表单的形式，提交数据
//    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
//
//    // 连接管理器
//    private static PoolingHttpClientConnectionManager pool;
//
//    // 请求配置
//    private static RequestConfig requestConfig;
//
//    static {
//        try {
//            SSLContextBuilder builder = new SSLContextBuilder();
//            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
//            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
//            // 配置同时支持 HTTP 和 HTTPS
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
//                    .<ConnectionSocketFactory>create()
//                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
//                    .register("https", sslsf)
//                    .build();
//
//            // 初始化连接管理器
//            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
//            pool.setMaxTotal(200);
//            // 设置最大路由
//            pool.setDefaultMaxPerRoute(2);
//
//            // 根据默认超时限制初始化 requestConfig
//            int socketTimeout = 10000;
//            int connectTimeout = 10000;
//            int connectionRequestTimeout = 10000;
//
//            // 设置请求超时时间
//            requestConfig = RequestConfig.custom()
//                    .setSocketTimeout(socketTimeout)
//                    .setConnectTimeout(connectTimeout)
//                    .setConnectionRequestTimeout(connectionRequestTimeout)
//                    .build();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static CloseableHttpClient getHttpClient() {
//        CloseableHttpClient httpClient = HttpClients.custom()
//                // 设置连接池管理
//                .setConnectionManager(pool)
//                // 设置请求配置
//                .setDefaultRequestConfig(requestConfig)
//                // 设置重试次数
//                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
//                .build();
//
//        return httpClient;
//    }
//
//    /**
//     * 发送 Post 请求
//     * @param httpPost
//     * @return
//     */
//    private static String sendHttpPost(HttpPost httpPost){
//        // 配置请求信息
//        httpPost.setConfig(requestConfig);
//        return sendHttpRequest(httpPost);
//    }
//
//    /**
//     * 发送 Get 请求
//     * @param httpGet
//     * @return
//     */
//    private static String sendHttpGet(HttpGet httpGet) {
//        // 配置请求信息
//        httpGet.setConfig(requestConfig);
//        return sendHttpRequest(httpGet);
//    }
//
//    private static String sendHttpRequest(HttpUriRequest httpUriRequest) {
//        CloseableHttpClient httpClient;
//        CloseableHttpResponse response = null;
//
//        // 响应内容
//        String responseContent = null;
//
//        try {
//            // 创建默认的 httpClient 实例
//            httpClient = getHttpClient();
//
//            // 执行请求
//            response = httpClient.execute(httpUriRequest);
//            // 得到响应实例
//            HttpEntity entity = response.getEntity();
//
//            // 可获得响应头
////            Header[] headers = response.getHeaders(CONTENT_TYPE);
////            for (Header header : headers) {
////                System.out.println(header.getName());
////            }
//
//            // 得到响应类型
////            System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());
//
//            // 判断响应状态
//            if (response.getStatusLine().getStatusCode() >= 300) {
//                throw new Exception("HTTP Request is not success, Response code is "+response.getStatusLine().getStatusCode());
//            }
//            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
//                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
//                EntityUtils.consume(entity);
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return responseContent;
//    }
//
//    /**
//     * 发送 post 请求
//     * @param httpUrl
//     * @return
//     */
//    public static String sendHttpPost(String httpUrl){
//        // 创建 httpPost
//        HttpPost httpPost = new HttpPost(httpUrl);
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 get 请求
//     * @param httpUrl
//     * @return
//     */
//    public static String sendHttpGet(String httpUrl){
//        // 创建 httpPost
//        HttpGet httpGet = new HttpGet(httpUrl);
//        return sendHttpGet(httpGet);
//    }
//
//    /**
//     * 发送 post 请求
//     * @param httpUrl
//     *              地址
//     * @param params
//     *              参数
//     * @param fileLists
//     *              附件
//     * @return
//     */
//    public static String sendHttpPost(String httpUrl, Map<String, String> params, List<File> fileLists) {
//        // 创建 httpPost
//        HttpPost httpPost = new HttpPost(httpUrl);
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        if (params != null) {
//            for (String key : params.keySet()) {
//                multipartEntityBuilder.addPart(key, new StringBody(params.get(key), TEXT_PLAIN));
//            }
//        }
//        if (fileLists != null) {
//            for (File file : fileLists) {
//                FileBody fileBody = new FileBody(file);
//                multipartEntityBuilder.addPart("files", fileBody);
//            }
//        }
//        HttpEntity requestEntity = multipartEntityBuilder.build();
//        httpPost.setEntity(requestEntity);
//        return sendHttpPost(httpPost);
//    }
//
//
//    /**
//     * 发送 post 请求
//     * @param httpUrl
//     *              地址
//     * @param params
//     *              参数（格式key1=value1&key2=value2）
//     * @return
//     */
//    public static String sendHttpPost(String httpUrl, String params){
//        HttpPost httpPost = new HttpPost(httpUrl);
//        try {
//            if (params != null && params.trim().length() > 0) {
//                StringEntity stringEntity = new StringEntity(params, CHARSET_UTF_8);
//                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
//                httpPost.setEntity(stringEntity);
//            }
//        }
//        catch (Exception e){
//
//        }
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 post 请求
//     * @param httpUrl
//     *              地址
//     * @param params
//     *              参数
//     * @return
//     */
//    public static String sendHttpPost(String httpUrl, Map<String, String> params){
//        String param = convertStringParamter(params);
//        return sendHttpPost(httpUrl, param);
//    }
//
//    /**
//     * 发送 post 请求 发送 json 数据
//     * @param httpUrl
//     *              地址
//     * @param paramsJson
//     *              参数（格式 json）
//     * @return
//     */
//    public static String sendHttpPostJson(String httpUrl, String paramsJson) {
//        HttpPost httpPost = new HttpPost(httpUrl);
//        if (paramsJson != null && paramsJson.trim().length() > 0) {
//            StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
//            stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
//            httpPost.setEntity(stringEntity);
//        }
//
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 post 请求 发送 xml 数据
//     * @param httpUrl
//     *              地址
//     * @param paramsXml
//     *              参数（格式 xml）
//     * @return
//     */
//    public static String sendHttpPostXml(String httpUrl, String paramsXml){
//        HttpPost httpPost = new HttpPost(httpUrl);
//        if (paramsXml != null && paramsXml.trim().length() > 0) {
//            StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
//            stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
//            httpPost.setEntity(stringEntity);
//        }
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 将 map 集合的键值对转化成：key1=value1&key2=value2 的形式
//     * @param parameterMap
//     *          需要转化的键值对集合
//     * @return
//     *          字符串
//     */
//    public static String convertStringParamter(Map parameterMap){
//        StringBuffer parameterBuffer = new StringBuffer();
//        if (parameterMap!=null){
//            Iterator iterator = parameterMap.keySet().iterator();
//            String key = null;
//            String value = null;
//            while (iterator.hasNext()) {
//                key = (String)iterator.next();
//                if (parameterMap.get(key)!=null){
//                    value = (String) parameterMap.get(key);
//                }
//                else{
//                    value = "";
//                }
//                parameterBuffer.append(key).append("=").append(value);
//                if (iterator.hasNext()){
//                    parameterBuffer.append("&");
//                }
//            }
//        }
//
//        return parameterBuffer.toString();
//    }
//}
