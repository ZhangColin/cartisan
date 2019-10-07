package com.cartisan.common.configs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author colin
 */
@Configuration
public class JsonConfig {

    @Bean(name = "innerObjectMapper")
    public ObjectMapper innerObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.getFactory().enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

//        objectMapper.setDateFormat(new CustomDateFormat(jacksonObjectMapper.getDateFormat()));
//        objectMapper.setConfig(objectMapper.getSerializationConfig()
//                .with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).with(TimeZone.getDefault()));
//        objectMapper.setConfig(objectMapper.getDeserializationConfig()
//                .with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).with(TimeZone.getDefault()));

        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        objectMapper
//                .registerModule(new SimpleModule().addSerializer(BigDecimal.class, new AmountJsonSerializer())
//                        .addSerializer(LocalDateTime.class, new DateTimeSerializer())
//                        .addDeserializer(LocalDateTime.class, new DateTimeDeserializer()));
//
//        objectMapper.setPropertyNamingStrategy(new JsonPropertyNamingStrategy());

        return objectMapper;
    }

    @Primary
    @Bean(name = "outerObjectMapper")
    public ObjectMapper outerObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.getFactory().enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

//        objectMapper.setDateFormat(new CustomDateFormat(jacksonObjectMapper.getDateFormat()));
//        objectMapper.setConfig(objectMapper.getSerializationConfig()
//                .with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).with(TimeZone.getDefault()));
//        objectMapper.setConfig(objectMapper.getDeserializationConfig()
//                .with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).with(TimeZone.getDefault()));

        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//
//        objectMapper.registerModule(simpleModule);

//        objectMapper
//                .registerModule(new SimpleModule().addSerializer(BigDecimal.class, new AmountJsonSerializer())
//                        .addSerializer(LocalDateTime.class, new DateTimeSerializer())
//                        .addDeserializer(LocalDateTime.class, new DateTimeDeserializer()));
//
//        objectMapper.setPropertyNamingStrategy(new JsonPropertyNamingStrategy());

        return objectMapper;
    }
}
