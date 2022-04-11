package org.metahut.starfish.server.controller;

import org.metahut.starfish.parser.exception.AbstractMetaParserException;
import org.metahut.starfish.service.AbstractInstanceService;
import org.metahut.starfish.service.AbstractMetaDataService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@SpringBootTest
public class MetaDataControllerTest {

    @Autowired
    private AbstractMetaDataService<String,Long,Object> metaDataService;

    @Autowired
    private AbstractInstanceService<String,Long,Object> instanceService;

    @Test
    @Transactional
    public void testSave() throws AbstractMetaParserException {
        String typeName = "HiveTable";
        Map<String,Object> properties = new HashMap<>();
        properties.put("name","dwd.user_info");
        Long id = metaDataService.create(typeName,0L,properties);
        metaDataService.delete(typeName, Arrays.asList(id));
    }

    @Test
    @Transactional
    public void testSaveRelation() throws AbstractMetaParserException {
        String typeName1 = "HiveTable";
        Map<String,Object> properties1 = new HashMap<>();
        properties1.put("name","dwd.user_info");
        Long headId = metaDataService.create(typeName1,0L,properties1);
        String typeName2 = "HiveColumn";
        Map<String,Object> properties2 = new HashMap<>();
        properties2.put("name","username");
        Long tailId1 = metaDataService.create(typeName2,0L,properties2);
        String typeName3 = "HiveColumn";
        Map<String,Object> properties3 = new HashMap<>();
        properties3.put("name","username");
        Long tailId2 = metaDataService.create(typeName3,0L,properties3);
        metaDataService.link("rel",headId,tailId1,"columns");
        metaDataService.link("rel",headId,tailId2,"columns");
        metaDataService.delete(typeName1,Arrays.asList(headId,tailId1,tailId2));
    }

}