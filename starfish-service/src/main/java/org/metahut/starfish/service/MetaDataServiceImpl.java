package org.metahut.starfish.service;

import org.metahut.starfish.parser.exception.AbstractMetaParserException;
import org.metahut.starfish.parser.function.*;

/**
 *
 */
public class MetaDataServiceImpl extends AbstractMetaDataService<String,String,String> {


    @Override
    protected AbstractSqlGraphService<String, String, String> graphApi() {
        return null;
    }

    @Override
    protected AbstractEnvironmentService<String, String> environmentApi() {
        return null;
    }

    @Override
<<<<<<< HEAD
    protected AbstractTypeService<String, String> classApi() {
=======
    protected AbstractClassService<String, String> classApi() {
>>>>>>> b40d1a915da00faceb19172ea1e908f2cb36a9f8
        return null;
    }

    @Override
<<<<<<< HEAD
    protected AbstractTypeInstanceBridgeService<String, String> classInstanceBridgeApi() {
=======
    protected AbstractClassInstanceBridgeService<String, String> classInstanceBridgeApi() {
>>>>>>> b40d1a915da00faceb19172ea1e908f2cb36a9f8
        return null;
    }

    @Override
    public void move(String toEnv, String fromEnv, long... classIds) throws AbstractMetaParserException {

    }

    @Override
    public void move(String toEnv, String fromEnv, String... instanceIds) throws AbstractMetaParserException {

    }
}
