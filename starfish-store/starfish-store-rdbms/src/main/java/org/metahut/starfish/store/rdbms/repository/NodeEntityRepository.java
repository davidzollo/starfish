package org.metahut.starfish.store.rdbms.repository;

import org.metahut.starfish.store.rdbms.entity.NodeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeEntityRepository extends JpaRepository<NodeEntity, Long>,
    JpaSpecificationExecutor<NodeEntity> {

    List<NodeEntity> removeByName(String name);

    List<NodeEntity> removeByCategory(String category);

}
