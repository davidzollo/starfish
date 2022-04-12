package org.metahut.starfish.store.rdbms.dao;

import org.metahut.starfish.store.dao.INodeEntityMapper;
import org.metahut.starfish.store.dao.IRelationEntityMapper;
import org.metahut.starfish.store.model.AbstractRelationEntity;
import org.metahut.starfish.store.rdbms.entity.RelationEntity;
import org.metahut.starfish.store.rdbms.repository.RelationEntityRepositoryTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Commit
@Transactional
@SpringBootTest
public class RelationEntityMapperTest {

    @Autowired
    private IRelationEntityMapper mapper;

    @Autowired
    private INodeEntityMapper nodeEntityMapper;

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static final String jsonFilePath = "/json/relation_entity.json";

    public static final String jsonFilePathTestData = "/json/multi_relation_entity.json";

    static Stream<RelationEntity> relationEntityProvider() throws IOException {
        RelationEntity entity = objectMapper.readValue(RelationEntityRepositoryTest.class.getResourceAsStream(jsonFilePath), RelationEntity.class);
        return Stream.of(entity);
    }

    static Stream<List<RelationEntity>> multiRelationEntityProvider() throws IOException {
        List<RelationEntity> entities = objectMapper.readValue(RelationEntityRepositoryTest.class.getResourceAsStream(jsonFilePathTestData), new TypeReference<List<RelationEntity>>() {});
        return Stream.of(entities);
    }

    @BeforeEach
    public void clear() {
        nodeEntityMapper.removeAll();
        mapper.removeAll();
    }

    @ParameterizedTest
    @MethodSource("relationEntityProvider")
    public void saveTest(RelationEntity relation) {
        nodeEntityMapper.create(relation.getStartNodeEntity());
        nodeEntityMapper.create(relation.getEndNodeEntity());

        AbstractRelationEntity  savedRelationEntity = mapper.create(relation);
        Assertions.assertAll(
            () -> Assertions.assertEquals(savedRelationEntity.getName(), relation.getName()),
            () -> Assertions.assertEquals(savedRelationEntity.getCategory(), relation.getCategory()),
            () -> Assertions.assertNotNull(savedRelationEntity.getCreateTime()),
            () -> Assertions.assertNotNull(savedRelationEntity.getUpdateTime())
        );
    }

    @ParameterizedTest
    @MethodSource("relationEntityProvider")
    public void removeAllByNameTest(RelationEntity relation) {
        nodeEntityMapper.create(relation.getStartNodeEntity());
        nodeEntityMapper.create(relation.getEndNodeEntity());

        mapper.create(relation);

        Assertions.assertDoesNotThrow(() -> mapper.removeAllByName(relation.getName()));
    }

    @ParameterizedTest
    @MethodSource("relationEntityProvider")
    public void removeAllByCategoryTest(RelationEntity relation) {
        nodeEntityMapper.create(relation.getStartNodeEntity());
        nodeEntityMapper.create(relation.getEndNodeEntity());

        mapper.create(relation);

        Assertions.assertDoesNotThrow(() -> mapper.removeAllByCategory(relation.getCategory()));
    }

    @ParameterizedTest
    @MethodSource("relationEntityProvider")
    public void removeAllByStartNodeEntityAndEndNodeEntityTest(RelationEntity relation) {
        nodeEntityMapper.create(relation.getStartNodeEntity());
        nodeEntityMapper.create(relation.getEndNodeEntity());

        mapper.create(relation);

        Assertions.assertDoesNotThrow(() ->
            mapper.removeAllByStartNodeEntityAndEndNodeEntity(
                relation.getStartNodeEntity(),
                relation.getEndNodeEntity()
        ));
    }

    @ParameterizedTest
    @MethodSource("relationEntityProvider")
    public void removeAllByStartNodeEntityAndEndNodeEntityAndCategoryTest(RelationEntity relation) {
        nodeEntityMapper.create(relation.getStartNodeEntity());
        nodeEntityMapper.create(relation.getEndNodeEntity());

        mapper.create(relation);

        Assertions.assertDoesNotThrow(() ->
            mapper.removeAllByStartNodeEntityAndEndNodeEntityAndCategory(
                relation.getStartNodeEntity(),
                relation.getEndNodeEntity(),
                relation.getCategory()
            ));
    }

    @ParameterizedTest
    @MethodSource("multiRelationEntityProvider")
    public void findByStartNodeEntityTest(List<RelationEntity> relations) {
        relations.stream().forEach(relation -> {
            nodeEntityMapper.create(relation.getStartNodeEntity());
            nodeEntityMapper.create(relation.getEndNodeEntity());
            mapper.create(relation);
        });

        RelationEntity expect = relations.stream().findFirst().get();

        Collection<RelationEntity> collection = mapper.findByStartNodeEntity(expect.getStartNodeEntity());

        RelationEntity actual = collection.stream().findFirst().get();

        Assertions.assertEquals(expect.toString(), actual.toString());
    }

    @ParameterizedTest
    @MethodSource("multiRelationEntityProvider")
    public void findByStartNodeEntityAndEndNodeEntityTest(List<RelationEntity> relations) {
        relations.stream().forEach(relation -> {
            nodeEntityMapper.create(relation.getStartNodeEntity());
            nodeEntityMapper.create(relation.getEndNodeEntity());
            mapper.create(relation);
        });

        RelationEntity expect = relations.stream().findFirst().get();

        Collection<RelationEntity> collection = mapper.findByStartNodeEntityAndEndNodeEntity(expect.getStartNodeEntity(), expect.getEndNodeEntity());

        RelationEntity actual = collection.stream().findFirst().get();

        Assertions.assertEquals(expect.toString(), actual.toString());
    }

    @ParameterizedTest
    @MethodSource("multiRelationEntityProvider")
    public void findByStartNodeEntityAndEndNodeEntityAndCategoryTest(List<RelationEntity> relations) {
        relations.stream().forEach(relation -> {
            nodeEntityMapper.create(relation.getStartNodeEntity());
            nodeEntityMapper.create(relation.getEndNodeEntity());
            mapper.create(relation);
        });

        RelationEntity expect = relations.stream().findFirst().get();

        Collection<RelationEntity> collection = mapper.findByStartNodeEntityAndEndNodeEntityAndCategory(expect.getStartNodeEntity(), expect.getEndNodeEntity(), expect.getCategory());

        RelationEntity actual = collection.stream().findFirst().get();

        Assertions.assertEquals(expect.toString(), actual.toString());
    }

}
