package base.core.kafka;

import org.springframework.kafka.annotation.KafkaListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer {
    static Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "ORDER_EVENT", groupId = "eventstore-group")
    public void consumeOrderEvent(Object order) {
        logger.info("Received Order Event: {}", order);
    }
}
