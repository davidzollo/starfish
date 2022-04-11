package org.metahut.starfish.store.rdbms.repository;

import org.metahut.starfish.store.rdbms.entity.NodeEntity;
import org.metahut.starfish.store.rdbms.entity.RelationEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RelationEntityRepository extends JpaRepository<RelationEntity, Long>,
    JpaSpecificationExecutor<RelationEntity> {

    List<RelationEntity> removeByName(String name);

    List<RelationEntity> removeByCategory(String category);

    List<RelationEntity> removeByStartNodeEntityAndEndNodeEntity(NodeEntity startNodeEntity, NodeEntity endNodeEntity);

    List<RelationEntity> removeByStartNodeEntity(NodeEntity startNodeEntity);

    List<RelationEntity> removeByEndNodeEntity(NodeEntity endNodeEntity);

}