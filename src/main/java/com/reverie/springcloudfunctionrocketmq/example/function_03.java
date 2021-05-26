package com.reverie.springcloudfunctionrocketmq.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import lombok.Data;
import reactor.core.publisher.Flux;

/**
 * @author oumengfan <oumengfan@kuaishou.com>
 * Created on 2021-05-26
 */
public class function_03 {

    // 输入，输出参数为 Message类型
    // 当入参是Message类型时，Http请求的Header会存放到Message的Header中，
    // 比如下面这样可以通过Message.getHeaders()拿到Http请求的所有Header信息。
    @Bean
    public Function<Message<String>, Message<String>> functionMessage() {
        return message -> {
            String payload = "response：" + message.getPayload();
            System.out.println("请求的Headers是：" + message.getHeaders());
            Map<String, Object> headers = new HashMap<>();
            headers.put("responseTime", LocalDateTime.now());
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            return MessageBuilder.createMessage(payload, messageHeaders);
        };
    }

    @Bean
    public Consumer<Flux<Message<User>>> consumerMessage() {
        return flux -> flux.subscribe(message -> System.out.println("收到User消息：" + message.getPayload() + "，消息头是：" + message.getHeaders()));
    }

    @Data
    public static class User {
        private Long id;
        private String name;
    }
}
