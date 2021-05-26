package com.reverie.springcloudfunctionrocketmq.example;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;

import lombok.Data;
import reactor.core.publisher.Flux;

/**
 * @author oumengfan <oumengfan@kuaishou.com>
 * Created on 2021-05-26
 */
public class function_02 {

    @Data
    public static class User {
        private Long id;
        private String name;
    }

    //返回亦可以是对象，对象以json格式返回给客户端
    // post请求 发送json类型{"id":1,"name":"zhangsan"}的body
    @Bean
    public Function<User, User> functionUser() {
        return input -> {
            input.setName("output:" + input.getName());
            return input;
        };
    }

    @Bean
    public Consumer<Flux<User>> consumerUser() {
        return flux -> flux.subscribe(user -> System.out.println("收到User消息：" + user));
    }

    @Bean
    public Supplier<User> supplierUser() {
        return () -> {
            User user = new User();
            user.setId(System.currentTimeMillis());
            user.setName("User-" + System.nanoTime());
            return user;
        };
    }
}
