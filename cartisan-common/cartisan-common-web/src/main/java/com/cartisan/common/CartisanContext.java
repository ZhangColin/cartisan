package com.cartisan.common;

import com.cartisan.common.exceptions.CartisanException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author colin
 */
@Lazy(false)
@Component
public class CartisanContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            throw new CartisanException(ContextCodeMessage.APPLICATION_CONTEXT_IS_NULL);
        }
        return Objects.requireNonNull(applicationContext.getBean(clazz));
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return Objects.requireNonNull(applicationContext.getBean(name, clazz));
    }


    private static final ThreadLocal<CurrentUser> userHolder = new ThreadLocal<>();

    public static void setCurrentUser(CurrentUser currentUser) {
        userHolder.set(currentUser);
    }

    public static CurrentUser getCurrentUser(CurrentUser currentUser) {
        return userHolder.get();
    }

    public static void cleanCurrentUser() {
        userHolder.remove();
    }

}
