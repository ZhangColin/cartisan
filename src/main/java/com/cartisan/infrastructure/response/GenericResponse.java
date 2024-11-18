package com.cartisan.infrastructure.response;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通用请求返回对象。
 *
 * <p>
 *      所有预留字段：<br>
 * 		code = 状态码 <br>
 * 		message = 描述信息 <br>
 * 		data = 携带对象 <br>
 * </p>
 *
 * @author zhangcolin
 */
public class GenericResponse extends LinkedHashMap<String, Object> implements Serializable {

    public static final String SUCCESS_MESSAGE = "success";
    public static final String ERROR_MESSAGE = "error";
    public static final String CODE_KEY = "code";
    public static final String MESSAGE_KEY = "message";
    public static final String DATA_KEY = "data";

    public static final int SUCCESS_CODE = 200;
    public static final int ERROR_CODE = 500;

    @Serial
    private static final long serialVersionUID = 1L;

    public GenericResponse() {
    }


    public GenericResponse(int code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public GenericResponse(int code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public GenericResponse(Map<String, ?> data) {
        this.setMap(data);
    }

    public static GenericResponse success() {
        return new GenericResponse(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static GenericResponse success(String message) {
        return new GenericResponse(SUCCESS_CODE, message);
    }

    public static GenericResponse success(Object data) {
        return new GenericResponse(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static GenericResponse error() {
        return new GenericResponse(ERROR_CODE, ERROR_MESSAGE);
    }

    public static GenericResponse error(String message) {
        return new GenericResponse(ERROR_CODE, message);
    }

    public static GenericResponse code(int code) {
        return new GenericResponse(code, null);
    }

    public static GenericResponse empty() {
        return new GenericResponse();
    }

    public Integer getCode() {
        return (Integer) this.get(CODE_KEY);
    }

    public GenericResponse setCode(int code) {
        this.put(CODE_KEY, code);
        return this;
    }

    public String getMessage() {
        return (String) this.get(MESSAGE_KEY);
    }

    public GenericResponse setMessage(String message) {
        this.put(MESSAGE_KEY, message);
        return this;
    }

    public Object getData() {
        return this.get(DATA_KEY);
    }

    public GenericResponse setData(Object data) {
        this.put(DATA_KEY, data);
        return this;
    }

    public GenericResponse set(String key, Object data) {
        this.put(key, data);
        return this;
    }

    public <T> T get(String key, Class<T> clazz) {
        return Convert.convert(clazz, this.get(key));
    }

    public <T> T getData(Class<T> clazz) {
        return Convert.convert(clazz, this.get(DATA_KEY));
    }

    public GenericResponse setMap(Map<String, ?> map) {
        for (String key : map.keySet()) {
            this.put(key, map.get(key));
        }
        return this;
    }

    public GenericResponse setJsonString(String jsonString) {
        return setMap(JSONUtil.toBean(jsonString, new TypeReference<>() {
        }, false));
    }

    public GenericResponse removeDefaultFields() {
        this.remove(CODE_KEY);
        this.remove(MESSAGE_KEY);
        this.remove(DATA_KEY);

        return this;
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
