package com.cartisan.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * @author colin
 */
@Component
public class CartisanJackson2ObjectMapperBuilderCustomizer implements Jackson2ObjectMapperBuilderCustomizer, Ordered {
    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        // 若 POJO 对象的属性值为 null，序列化时不进行显示
        // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        // 若 POJO 对象的属性值为""，序列化时不进行显示
        // jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.ALWAYS);
        // 序列化时的全名策略————驼峰命名法
        jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        // 根据属性名称排序
        // jacksonObjectMapperBuilder.featuresToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

        // 针对于 Date 类型，文本格式化
        jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));

        // 针对于 JDK 新时间类。序列化时带有 T 的问题，自定义格式化字符串
        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        final SimpleModule amountModule = new SimpleModule();
        amountModule.addSerializer(BigDecimal.class, new AmountJsonSerializer());

        final SimpleModule longModule = new SimpleModule();
        longModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        longModule.addSerializer(Long.class, ToStringSerializer.instance);

        jacksonObjectMapperBuilder.modules(javaTimeModule, amountModule, longModule);

        // 禁止 Date 类型转化为时间戳
        // 默认开启，将 Date 类型序列化为数字时间戳（毫秒表示）。关闭后，序列化为文本表现形式（2020-03-17T01:58:58.308+0000）
        // 若设置时间格式化。那么均输出格式化的时间类型
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 默认关闭，即以文本（ISO-8601）作为 Key，开启后，以时间戳作为 Key
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        // 默认关闭，将 char[] 数组序列化为 String 类型。若开启后序列化为 JSON 数组
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS);

        // 默认开启：如果一个类没有 public 的方法或属性时，会导致序列化失败。关闭后，会得到一个空 JSON 串
        // 序列化时候遇到空对象不抛出异常
        jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 默认禁用，禁用情况下，需要考虑 WRITE_ENUMS_USING_TO_STRING 配置。启用后，ENUM序列化为数字。
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        // 仅当 WRITE_ENUMS_USING_INDEX 为禁用时（默认禁用），该配置生效
        // 默认关闭，枚举类型序列化方式，默认情况下使用 Enum.name()。开启后，使用 Enum.toString()。注：需重写 Enum 的 toString() 方法
        jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);

        // 默认关闭。即使用 BigDecimal.toString() 序列化。开启后，使用 BigDecimal.toPlainString 序列化，不输出科学计数法的值。
        jacksonObjectMapperBuilder.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

        // 默认关闭，当 JSON 字段为 ""（EMPTY_STRING）时，解析为 POJO 对象抛出异常。开启后，该 POJO 的属性值为 NULL
        jacksonObjectMapperBuilder.featuresToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES，反序列化时候遇到不匹配的属性并不抛出异常
         jacksonObjectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //反序列化的时候如果是无效子类型,不抛出异常
        jacksonObjectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
