package com.reverie.springcloudfunctionrocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudFunctionRocketmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudFunctionRocketmqApplication.class, args);

        DefaultMQProducer producer = new DefaultMQProducer("test1");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
            for (int i=0; i<10; i++) {
                org.apache.rocketmq.common.message.Message message = new Message();
                message.setTopic("test-topic");
                message.setTags("tag1");
                message.setBody(("Hello"+i).getBytes());
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

}
