package base.core.kafka;

import org.springframework.kafka.core.KafkaTemplate;

public class Producer {
    private final KafkaTemplate<String, Object> kafkaTemplateObjectMsg;

    public Producer(KafkaTemplate<String, Object> kafkaTemplateObjectMsg) {
        this.kafkaTemplateObjectMsg = kafkaTemplateObjectMsg;
    }

    public void eventStoreInit(String topicName, String content) {
        kafkaTemplateObjectMsg.send(topicName, content);
    }
}