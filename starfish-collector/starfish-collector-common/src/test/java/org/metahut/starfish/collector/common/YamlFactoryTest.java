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

package org.metahut.starfish.collector.common;

import org.metahut.starfish.message.api.MessageProperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.metahut.starfish.collector.common.Constants.COLLECTOR_CONFIG_FILE;

public class YamlFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(YamlFactoryTest.class);

    @Test
    public void testLoadYamlToProperties() {
        Properties properties = YamlFactory.loadYamlToProperties(COLLECTOR_CONFIG_FILE);
        String value = properties.getProperty("starfish.message.type");
        logger.info("type value:{}", value);
        Assertions.assertNotNull(value);
    }

    @Test
    public void testParseObject() {
        MessageProperties messageProperties = YamlFactory.parseObject("starfish.message", COLLECTOR_CONFIG_FILE, new MessageProperties());
        Assertions.assertNotNull(messageProperties);
        logger.info("messageProperties:{}", messageProperties);
    }
}
