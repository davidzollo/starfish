package org.metahut.starfish.message.pulsar;

import org.metahut.starfish.message.api.MessageConsumer;
import org.metahut.starfish.message.api.MessageException;
import org.metahut.starfish.message.api.MessageManager;
import org.metahut.starfish.message.api.MessageProducer;
import org.metahut.starfish.message.api.MessageProperties;
import org.metahut.starfish.message.api.MessageType;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.metahut.starfish.message.api.Constants.MESSAGE_CONFIG_PREFIX;
import static org.metahut.starfish.message.api.MessageType.pulsar;

/**
 * pulsar meta message manager
 */
@Component
@ConditionalOnProperty(prefix = MESSAGE_CONFIG_PREFIX, name = "type", havingValue = "pulsar")
public class PulsarMessageManager implements MessageManager {

    private static final Logger logger = LoggerFactory.getLogger(PulsarMessageManager.class);

    private PulsarClient client;

    private MessageProperties.Pulsar pulsarProperties;

    private final Map<String, MessageProducer> messageProducerMap = new ConcurrentHashMap(16);
    private final Map<String, MessageConsumer> messageConsumerMap = new ConcurrentHashMap(16);

    @Autowired
    public PulsarMessageManager(MessageProperties messageProperties) throws MessageException {
        init(messageProperties);
    }

    public PulsarMessageManager() {

    }

    @Override
    public void init(MessageProperties messageProperties) throws MessageException {
        pulsarProperties = messageProperties.getPulsar();
        try {
            client = PulsarClient.builder()
                    .serviceUrl(pulsarProperties.getServiceUrl())
                    .build();
            this.setProducers(pulsarProperties.getProducers());
            this.setConsumers(pulsarProperties.getConsumers());

        } catch (PulsarClientException e) {
            throw new MessageException("Pulsar create client exception", e);
        }
    }

    public void setProducers(Map<String, MessageProperties.PulsarProducer> producerMap) {
        producerMap.forEach((name, properties) -> messageProducerMap.put(name, this.createProducer(properties)));
    }

    protected MessageProducer createProducer(MessageProperties.PulsarProducer properties) {
        try {
            return new PulsarMessageProducer(client.newProducer()
                    .topic(properties.getTopicName())
                    .producerName(properties.getProducerName())
                    .create());
        } catch (PulsarClientException e) {
            logger.error(e.getMessage(), e);
            // TODO Exception type???
            throw new IllegalArgumentException(e);
        }
    }

    public void setConsumers(Map<String, MessageProperties.PulsarConsumer> consumerMap) {
        consumerMap.forEach((name, properties) -> messageConsumerMap.put(name, this.createConsumer(properties)));
    }

    protected MessageConsumer createConsumer(MessageProperties.PulsarConsumer properties) {
        try {
            return new PulsarMessageConsumer(client.newConsumer()
                    .topic(properties.getTopicName())
                    .subscriptionName(properties.getSubscriptionName())
                    .subscribe());
        } catch (PulsarClientException e) {
            logger.error(e.getMessage(), e);
            // TODO Exception type???
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public MessageType getType() {
        return pulsar;
    }

    @Override
    public MessageProducer getProducer(String name) {
        return messageProducerMap.get(name);
    }

    @Override
    public MessageConsumer getConsumer(String name) {
        return messageConsumerMap.get(name);
    }

    @Override
    public void close() throws Exception {
        messageProducerMap.forEach((name, producer) -> {
            try {
                producer.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });

        messageConsumerMap.forEach((name, consumer) -> {
            try {
                consumer.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });

        if (Objects.nonNull(client)) {
            client.close();
        }
    }

}
