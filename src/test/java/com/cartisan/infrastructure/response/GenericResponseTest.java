package com.cartisan.infrastructure.response;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.cartisan.infrastructure.response.GenericResponse.success;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class GenericResponseTest {
    @Test
    public void generate_empty_response() {
        final GenericResponse successResponse = GenericResponse.empty() ;

        assertNotNull(successResponse);

        assertTrue(successResponse.isEmpty());

        log.info(successResponse.toString());
    }

    @Test
    public void generate_success_response() {
        final GenericResponse successResponse = success();

        assertNotNull(successResponse);
        assertEquals(successResponse.getCode(), GenericResponse.SUCCESS_CODE);
        assertEquals(successResponse.getMessage(), GenericResponse.SUCCESS_MESSAGE);

        assertFalse(successResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(successResponse.toString());
    }

    @Test
    public void generate_success_response_with_message() {
        final String message = "成功";

        final GenericResponse successResponse = success(message);

        assertNotNull(successResponse);
        assertEquals(successResponse.getCode(), GenericResponse.SUCCESS_CODE);
        assertEquals(successResponse.getMessage(), message);

        assertFalse(successResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(successResponse.toString());
    }

    @Test
    public void generate_success_response_with_data() {
        final JSONObject data = new JSONObject().putOnce("name", "colin");

        final GenericResponse successResponse = success(data);

        assertNotNull(successResponse);
        assertEquals(successResponse.getCode(), GenericResponse.SUCCESS_CODE);
        assertEquals(successResponse.getMessage(), GenericResponse.SUCCESS_MESSAGE);
        assertNotNull(successResponse.getData());

        log.info(successResponse.toString());
    }

    @Test
    public void generate_error_response() {
        final GenericResponse errorResponse = GenericResponse.error();

        assertNotNull(errorResponse);
        assertEquals(errorResponse.getCode(), GenericResponse.ERROR_CODE);
        assertEquals(errorResponse.getMessage(), GenericResponse.ERROR_MESSAGE);

        assertFalse(errorResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(errorResponse.toString());

    }

    @Test
    public void generate_error_response_with_message() {
        final String message = "失败";
        final GenericResponse errorResponse = GenericResponse.error(message);

        assertNotNull(errorResponse);
        assertEquals(errorResponse.getCode(), GenericResponse.ERROR_CODE);
        assertEquals(errorResponse.getMessage(), message);

        assertFalse(errorResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(errorResponse.toString());
    }

    @Test
    public void generate_response_with_code() {
        final int code = 401;
        final GenericResponse errorResponse = GenericResponse.code(401);

        assertNotNull(errorResponse);
        assertEquals(errorResponse.getCode(), code);
        assertNull(errorResponse.getMessage());

        assertFalse(errorResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(errorResponse.toString());
    }

    @Test
    public void generate_response_with_data() {
        final int code = 401;
        final GenericResponse errorResponse = GenericResponse.code(401);

        assertNotNull(errorResponse);
        assertEquals(errorResponse.getCode(), code);
        assertNull(errorResponse.getMessage());

        assertFalse(errorResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(errorResponse.toString());
    }

    @Test
    public void set_code() {
        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.setCode(GenericResponse.SUCCESS_CODE);

        assertEquals(genericResponse.getCode(), GenericResponse.SUCCESS_CODE);

        log.info(genericResponse.toString());
    }

    @Test
    public void set_message() {
        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.setMessage(GenericResponse.SUCCESS_MESSAGE);

        assertEquals(genericResponse.getMessage(), GenericResponse.SUCCESS_MESSAGE);

        log.info(genericResponse.toString());
    }

    @Test
    public void set_data() {
        final JSONObject data = new JSONObject().putOnce("name", "colin");
        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.setData(data);

        assertEquals(genericResponse.getData(), data);

        log.info(genericResponse.toString());
    }

    @Test
    public void set_anyway_data() {
        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.set("name", "colin");
        genericResponse.set("like", "ManUtd");

        assertEquals(genericResponse.get("name"), "colin");
        assertEquals(genericResponse.get("like"), "ManUtd");

        log.info(genericResponse.toString());
    }

    @Test
    public void set_map() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "colin");
        map.put("like", "ManUtd");

        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.setMap(map);

        assertEquals(genericResponse.get("name"), "colin");
        assertEquals(genericResponse.get("like"), "ManUtd");

        log.info(genericResponse.toString());
    }

    @Test
    public void set_json_string() {
        final GenericResponse genericResponse = new GenericResponse();

        genericResponse.setJsonString("{\"name\":\"colin\",\"like\":\"ManUtd\"}");

        assertEquals(genericResponse.get("name"), "colin");
        assertEquals(genericResponse.get("like"), "ManUtd");

        log.info(genericResponse.toString());
    }

    @Test
    public void get_data_by_key_with_class() {
        final GenericResponse genericResponse = new GenericResponse();
        final JSONObject data = new JSONObject().putOnce("name", "colin");

        genericResponse.set("jsonInfo", data);

        final JSONObject result = genericResponse.get("jsonInfo", JSONObject.class);

        assertNotNull(result);
        assertEquals(result.get("name"), "colin");

        log.info(result.toString());
    }

    @Test
    public void get_data_by_class() {
        final GenericResponse genericResponse = new GenericResponse();
        final JSONObject data = new JSONObject().putOnce("name", "colin");

        genericResponse.setData(data);

        final JSONObject result = genericResponse.getData(JSONObject.class);

        assertNotNull(result);
        assertEquals(result.get("name"), "colin");

        log.info(result.toString());
    }

    @Test
    public void get_data_by_not_match_class() {
        final GenericResponse genericResponse = new GenericResponse();
        final JSONObject data = new JSONObject().putOnce("name", "colin");

        genericResponse.setData(data);

        assertThrows(Exception.class, () -> genericResponse.getData(Integer.class));
    }

    @Test
    public void create_empty_generic_response() {
        final GenericResponse emptyResponse = new GenericResponse();
        assertNotNull(emptyResponse);
        assertTrue(emptyResponse.isEmpty());

        log.info(emptyResponse.toString());
    }

    @Test
    public void create_generic_response_with_code_and_message() {
        final GenericResponse genericResponse = new GenericResponse(GenericResponse.SUCCESS_CODE, GenericResponse.SUCCESS_MESSAGE);

        assertNotNull(genericResponse);
        assertEquals(genericResponse.getCode(), GenericResponse.SUCCESS_CODE);
        assertEquals(genericResponse.getMessage(), GenericResponse.SUCCESS_MESSAGE);
        assertFalse(genericResponse.containsKey(GenericResponse.DATA_KEY));

        log.info(genericResponse.toString());
    }

    @Test
    public void create_generic_response_with_code_and_message_and_data() {
        final JSONObject data = new JSONObject().putOnce("name", "colin");
        final GenericResponse genericResponse = new GenericResponse(GenericResponse.SUCCESS_CODE, GenericResponse.SUCCESS_MESSAGE, data);

        assertNotNull(genericResponse);
        assertEquals(genericResponse.getCode(), GenericResponse.SUCCESS_CODE);
        assertEquals(genericResponse.getMessage(), GenericResponse.SUCCESS_MESSAGE);
        assertNotNull(genericResponse.getData());

        log.info(genericResponse.toString());
    }

    @Test
    public void create_generic_response_by_map() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "colin");
        map.put("like", "ManUtd");

        final GenericResponse genericResponse = new GenericResponse(map);

        assertNotNull(genericResponse);
        assertEquals(genericResponse.get("name"), "colin");
        assertEquals(genericResponse.get("like"), "ManUtd");

        log.info(genericResponse.toString());
    }

    @Test
    public void remove_default_fields() {
        final GenericResponse successResponse = success();

        successResponse.removeDefaultFields();

        assertTrue(successResponse.isEmpty());

        log.info(successResponse.toString());
    }
}