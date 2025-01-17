package org.metahut.starfish.store.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public abstract class AbstractNodeEntity<I extends Serializable, P extends AbstractEntityProperty> implements Serializable {

    public abstract I getId();

    public abstract void setId(I id);

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getCategory();

    public abstract void setCategory(String category);

    public abstract Map<String, P> getKeyedProperties();

    public abstract void setKeyedProperties(Map<String, P> properties);

    public abstract Integer getOperator();

    public abstract void setOperator(Integer operator);

    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    public abstract Date getUpdateTime();

    public abstract void setUpdateTime(Date updateTime);
}
