package com.reverie.springcloudfunctionrocketmq.example;

import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

/**
 * @author oumengfan <oumengfan@kuaishou.com>
 * Created on 2021-05-26
 */
public class function_01 {

    // post 请求才会返回结果
    @Bean
    public Consumer<String> consumer() {
        return message -> System.out.println("收到消息：" + message);
    }

    // Consumer是只接收参数进行处理
    @Bean
    public Consumer<Flux<String>> consumerFlux() {
        return stringFlux -> {stringFlux.subscribe(str -> System.out.println("receive message : " + str));};
    }

    // Supplier是不接收参数但是会返回一个结果
    // http://localhost:8080/supplier get请求会返回结果
    @Bean
    public Supplier<String> supplier() {
        return () -> LocalDateTime.now().toString();
    }

    // Function可以接收一个参数进行处理后再返回一个结果
    // 我们以POST方式请求/uppercase时如果请求体中传递的数据
    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(String::toUpperCase);
    }
}
