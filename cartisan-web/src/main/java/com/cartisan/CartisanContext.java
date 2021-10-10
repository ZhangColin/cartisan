package com.cartisan;

import com.cartisan.constants.CodeMessage;
import com.cartisan.exception.CartisanException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author colin
 */
@Lazy(false)
@Component
@Slf4j
public class CartisanContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void setAppContext(ApplicationContext context) {
        if (!Objects.isNull(applicationContext)) {
            log.warn("CartisanContext 中的 ApplicationContext 被覆盖，原有 ApplicationContext 为："
                    + applicationContext);
        }
        applicationContext = context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        setAppContext(context);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return Objects.requireNonNull(applicationContext.getBean(requiredType));
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        assertApplicationContext();
        return Objects.requireNonNull(applicationContext.getBean(name, clazz));
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        assertApplicationContext();
        return Objects.requireNonNull(applicationContext.getBeansOfType(clazz));
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertApplicationContext() {
        if (null == applicationContext) {
            throw new CartisanException(CodeMessage.FAIL.fillArgs("applicationContext为空,请检查是否注入springContextHolder"));
        }
    }

}
