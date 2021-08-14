package aforo255.ms.test.pay.config;

import aforo255.ms.test.pay.util.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class BuilderKafkaTopics {

    @Bean
    public NewTopic paymentEvents(){
        return TopicBuilder.name(Constants.TOPIC_PAYMENT).partitions(3).replicas(1).build();
    }

}
