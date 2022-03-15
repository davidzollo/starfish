package org.metahut.starfish.parser.domain.enums;

/**
 *
 */
public class SfType<K> {
    private K key;
    private long serialVersionId;

    public SfType() {
    }

    public SfType(K key, long serialVersionId) {
        this.key = key;
        this.serialVersionId = serialVersionId;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public long getSerialVersionId() {
        return serialVersionId;
    }

    public void setSerialVersionId(long serialVersionId) {
        this.serialVersionId = serialVersionId;
    }
}