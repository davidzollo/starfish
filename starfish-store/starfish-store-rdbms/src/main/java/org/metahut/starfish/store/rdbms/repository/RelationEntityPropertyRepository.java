package org.metahut.starfish.store.rdbms.repository;

import java.util.List;
import org.metahut.starfish.store.rdbms.entity.RelationEntityProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationEntityPropertyRepository extends JpaRepository<RelationEntityProperty, Long>,
    JpaSpecificationExecutor<RelationEntityProperty> {

    List<RelationEntityProperty> removeByName(String name);
}
