//package com.cartisan.utils;
//
//import org.aspectj.weaver.ast.Var;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.convert.converter.ConverterFactory;
//
//import javax.annotation.Nullable;
//import java.lang.reflect.Method;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author colin
// */
//public class EnumMvcConverterFactory implements ConverterFactory<String, Enum<?>> {
//    private final ConcurrentHashMap<Class<? extends Enum<?>>, EnumMvcConverterHolder> holderMapper = new ConcurrentHashMap<>();
//    @Override
//    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
//        return null;
//    }
//
//    static class EnumMvcConverterHolder{
//        @Nullable
//        final EnumMvcConverter<?>
//    }
//
//    static class EnumMvcConverter<T extends Enum<T>> implements Converter<String, T> {
//        private final Method method;
//
//        public EnumMvcConverter(Method method) {
//            this.method = method;
//            this.method.setAccessible(true);
//        }
//
//        @Override
//        public T convert(String source) {
//            if (source.isEmpty()) {
//                return
//            }
//            return null;
//        }
//    }
//
//}
