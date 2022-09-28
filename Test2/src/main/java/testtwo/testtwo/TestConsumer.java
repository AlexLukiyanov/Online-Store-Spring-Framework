package testtwo.testtwo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {

    @KafkaListener(topics = "GeekTopic")
    public void msgListener(String message){
        System.out.println(message);
    }

}
