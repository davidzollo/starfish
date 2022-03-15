package org.metahut.starfish.parser.domain.instance;

import org.metahut.starfish.parser.domain.enums.SfRelType;

/**
 *
 */
public class SfAttribute {

    /**
     * Attribute Name
     */
    private String name;

    /**
     * class type name
     */
    private String className;

    /**
     * class type belongs to
     */
    private SfRelType sfRelType = SfRelType.DEFAULT;

    /**
     * Attribute Is Array Or Not
     */
    private boolean array;

    //private Boolean isProxy        = Boolean.FALSE;
    //private Boolean isIncomplete   = Boolean.FALSE;
    //private Integer provenanceType = 0;


    /**
     * Attribute Default Value
     * TODO provider 序列化等问题
     * TODO tag 解决 ?
     */
    private String defaultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SfRelType getRelType() {
        return sfRelType;
    }

    public void setRelType(SfRelType sfRelType) {
        this.sfRelType = sfRelType;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isArray() {
        return array;
    }

    public void setArray(boolean array) {
        this.array = array;
    }
}