package org.metahut.starfish.parser.domain.instance;

import java.util.Map;

/**
 *
 */
public class Node<K,T> {

    private K instanceId;

    private Map<String,T> values;

    public K getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(K instanceId) {
        this.instanceId = instanceId;
    }

    public Node() {
    }

    public Node(K instanceId, Map<String, T> values) {
        this.instanceId = instanceId;
        this.values = values;
    }

    public Map<String, T> getValues() {
        return values;
    }

    public void setValues(Map<String, T> values) {
        this.values = values;
    }
}
