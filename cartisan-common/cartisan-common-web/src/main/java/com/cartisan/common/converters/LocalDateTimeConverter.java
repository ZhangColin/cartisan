package com.cartisan.common.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author colin
 * 将LocalDateTime字段以时间戳的方式返回给前端
 *
 * 例：
 * @JsonSerialize(using = LocalDateTimeConverter.class)
 * protected LocalDateTime gmtModified;
 *
 * 将LocalDateTime字段以指定格式化日期的方式返回给前端
 * @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
 * protected LocalDateTime gmtModified;
 *
 * 对前端传入的日期进行格式化
 * @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 * protected LocalDateTime gmtModified;
 */
public class LocalDateTimeConverter extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }
}
