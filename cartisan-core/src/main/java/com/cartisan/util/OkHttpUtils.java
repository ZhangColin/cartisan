package com.cartisan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author colin
 */
public class OkHttpUtils {
    private static volatile OkHttpClient okHttpClient = null;
    private static volatile Semaphore semaphore = null;
    private Map<String, String> headerMap;
    private Map<String, String> paramMap;
    private String url;
    private Request.Builder request;

    private OkHttpUtils() {
        if (okHttpClient == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpClient == null) {
                    final TrustManager[] trustManagers = buildTrustManagers();
                    okHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .sslSocketFactory(createSSLSocketFactory(trustManagers), (X509TrustManager) trustManagers[0])
                            .hostnameVerifier((hostName, session) -> true)
                            .retryOnConnectionFailure(true)
                            .build();

                    addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                }
            }
        }
    }

    public OkHttpUtils url(String url) {
        this.url = url;
        return this;
    }

    public OkHttpUtils addParam(String key, String value) {
        if (paramMap == null) {
            paramMap = new LinkedHashMap<>(16);
        }

        paramMap.put(key, value);
        return this;
    }

    public OkHttpUtils addHeader(String key, String value) {
        if (headerMap == null) {
            headerMap = new LinkedHashMap<>(16);
        }

        headerMap.put(key, value);
        return this;
    }

    public OkHttpUtils get() {
        request = new Request.Builder().get();
        final StringBuilder urlBuilder = new StringBuilder(url);

        if (paramMap != null) {
            urlBuilder.append("?");
            try {
                for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), "utf-8"))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                            .append("&");
                }
            } catch (UnsupportedEncodingException e) {

            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        request.url(urlBuilder.toString());
        return this;
    }

    public OkHttpUtils post(boolean isJsonPost) {
        RequestBody requestBody;

        if (isJsonPost) {
            String json = "";
            if (paramMap != null) {
                try {
                    json = new ObjectMapper().writeValueAsString(paramMap);
                } catch (JsonProcessingException e) {
                }
            }
            requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        } else {
            final FormBody.Builder formBody = new FormBody.Builder();

            if (paramMap != null) {
                paramMap.forEach(formBody::add);
            }
            requestBody = formBody.build();
        }

        request = new Request.Builder().post(requestBody).url(url);
        return this;
    }

    public String sync() {
        setHeader(request);

        try {
            final Response response = okHttpClient.newCall(request.build()).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (IOException e) {
            return "请求失败：" + e.getMessage();
        }
    }

    public String async() {
        final StringBuilder buffer = new StringBuilder("");
        setHeader(request);
        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                buffer.append("请求出错：").append(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                buffer.append(response.body().string());
                getSemaphoreInstance().release();
            }
        });

        try {
            getSemaphoreInstance().acquire();
        } catch (InterruptedException e) {

        }

        return buffer.toString();
    }

    public void async(ICallBack callBack) {
        setHeader(request);

        okHttpClient.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure(call, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                callBack.onSuccessful(call, response.body().string());
            }
        });
    }

    private void setHeader(Request.Builder request) {
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public static OkHttpUtils builder() {
        return new OkHttpUtils();
    }

    /**
     * 用于异步请求时，控制访问线程数，返回结果
     *
     * @return
     */
    private static Semaphore getSemaphoreInstance() {
        // 只能 1 个线程同时访问
        synchronized (OkHttpUtils.class) {
            if (semaphore == null) {
                semaphore = new Semaphore(0);
            }
        }

        return semaphore;
    }

    /**
     * 生成安全套接字工厂，用于 https 请求的证书跳过
     *
     * @param trustAllCerts
     * @return
     */
    private static SSLSocketFactory createSSLSocketFactory(TrustManager[] trustAllCerts) {
        SSLSocketFactory factory = null;
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            factory = sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {

        }

        return factory;
    }

    private static TrustManager[] buildTrustManagers() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
    }

    public interface ICallBack{
        void onSuccessful(Call call, String data);
        void onFailure(Call call, String data);
    }

//    public static void main(String[] args) {
//        // get请求，方法顺序按照这种方式，切记选择post/get一定要放在倒数第二，同步或者异步倒数第一，才会正确执行
//        OkHttpUtils.builder().url("请求地址，http/https都可以")
//                // 有参数的话添加参数，可多个
//                .addParam("参数名", "参数值")
//                .addParam("参数名", "参数值")
//                // 也可以添加多个
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                .get()
//                // 可选择是同步请求还是异步请求
//                //.async();
//                .sync();
//
//        // post请求，分为两种，一种是普通表单提交，一种是json提交
//        OkHttpUtils.builder().url("请求地址，http/https都可以")
//                // 有参数的话添加参数，可多个
//                .addParam("参数名", "参数值")
//                .addParam("参数名", "参数值")
//                // 也可以添加多个
//                .addHeader("Content-Type", "application/json; charset=utf-8")
//                // 如果是true的话，会类似于postman中post提交方式的raw，用json的方式提交，不是表单
//                // 如果是false的话传统的表单提交
//                .post(true)
//                .sync();
//
//        // 选择异步有两个方法，一个是带回调接口，一个是直接返回结果
//        OkHttpUtils.builder().url("")
//                .post(false)
//                .async();
//
//        OkHttpUtils.builder().url("").post(false).async(new OkHttpUtils.ICallBack() {
//            @Override
//            public void onSuccessful(Call call, String data) {
//                // 请求成功后的处理
//            }
//
//            @Override
//            public void onFailure(Call call, String errorMsg) {
//                // 请求失败后的处理
//            }
//        });
//    }
}
