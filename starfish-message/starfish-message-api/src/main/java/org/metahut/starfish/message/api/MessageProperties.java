/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.metahut.starfish.message.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.metahut.starfish.message.api.Constants.MESSAGE_CONFIG_PREFIX;

@Configuration
@ConfigurationProperties(prefix = MESSAGE_CONFIG_PREFIX)
public class MessageProperties {

    private MessageType type;
    private final Kafka kafka = new Kafka();
    private final Pulsar pulsar = new Pulsar();

    public static class Kafka {

    }

    public static class Pulsar {

        private String serviceUrl;

        private Map<String, PulsarProducer> producers;

        private Map<String, PulsarConsumer> consumers;

        public String getServiceUrl() {
            return serviceUrl;
        }

        public void setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
        }

        public Map<String, PulsarProducer> getProducers() {
            return producers;
        }

        public void setProducers(Map<String, PulsarProducer> producers) {
            this.producers = producers;
        }

        public Map<String, PulsarConsumer> getConsumers() {
            return consumers;
        }

        public void setConsumers(Map<String, PulsarConsumer> consumers) {
            this.consumers = consumers;
        }

        @Override
        public String toString() {
            return "Pulsar{"
                    + "serviceUrl='" + serviceUrl
                    + '\''
                    + ", producers=" + producers
                    + ", consumers=" + consumers
                    + '}';
        }
    }

    public static class PulsarProducer {

        private String topicName;

        private String schema;

        private String producerName;

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getProducerName() {
            return producerName;
        }

        public void setProducerName(String producerName) {
            this.producerName = producerName;
        }

        @Override
        public String toString() {
            return "PulsarProducer{"
                    + "topicName='"
                    + topicName
                    + '\''
                    + ", schema='"
                    + schema + '\''
                    + ", producerName='"
                    + producerName
                    + '\''
                    + '}';
        }
    }

    public static class PulsarConsumer {

        private String topicName;

        private String subscriptionName;

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getSubscriptionName() {
            return subscriptionName;
        }

        public void setSubscriptionName(String subscriptionName) {
            this.subscriptionName = subscriptionName;
        }

        @Override
        public String toString() {
            return "PulsarConsumer{"
                    + "topicName='"
                    + topicName
                    + '\''
                    + ", subscriptionName='"
                    + subscriptionName
                    + '\''
                    + '}';
        }
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Kafka getKafka() {
        return kafka;
    }

    public Pulsar getPulsar() {
        return pulsar;
    }

    @Override
    public String toString() {
        return "MessageProperties{"
                + "type="
                + type
                + ", kafka="
                + kafka
                + ", pulsar="
                + pulsar
                + '}';
    }
}
