package com.cartisan;

import com.cartisan.infrastructure.util.SnowflakeIdWorker;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangcolin
 */
@MapperScan(value = "com.cartisan.**.mapper")
@SpringBootApplication
@Slf4j
public class CartisanApplication {
    public static void main(String[] args) {
                /* 在指定的目录下生成应用pid，执行下面的命令关闭应用 :
            'cat /Users/zhangcolin/app.pid | xargs kill' */
//        SpringApplication application = new SpringApplication(EkinApplication.class);
//        application.addListeners(new ApplicationPidFileWriter("/Users/zhangcolin/ekin.pid"));
//        application.run(args);
        SpringApplication.run(CartisanApplication.class, args);
    }

    @Bean
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(1, 1);
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("Cartisan application shutdown");
    }
}
