package org.metahut.starfish.service;

import org.metahut.starfish.parser.domain.enums.Type;

import java.util.Collection;
import java.util.Set;

/**
 *
 */
public abstract class AbstractTypeInstanceBridgeService<E,K> implements ITypeInstanceBridgeApi<E, K> {

    @Override
    public void save(E env, Type<K> rel) {

    }

    @Override
    public Set<Type<K>> query(E env) {
        return null;
    }

    @Override
    public Type<K> query(E env, K instanceId) {
        return null;
    }

    @Override
    public Set<Type<K>> query(E env, K... instanceId) {
        return null;
    }

    @Override
    public Collection<Long> copy(E toEnv, E fromEnv, Collection<K> instanceIds) {
        return null;
    }

    @Override
    public void delete(E env) {

    }

    @Override
    public void delete(E env, Collection<K> instanceIds) {

    }

    @Override
    public void delete(E env, Type<K> rel) {

    }
}
