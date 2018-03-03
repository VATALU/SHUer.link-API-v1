package org.shuerlink.util;


import org.shuerlink.model.dto.MongoSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class MongoAutoidUtil {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private MongoTemplate mongoTemplate;

    public int getNextCollectionId(String cardId) {
        try {
            MongoSequence seq = mongoTemplate.findAndModify(
                    new Query(Criteria.where("_id").is(cardId)),
                    new Update().inc("collectionId", 1),
                    FindAndModifyOptions.options().upsert(true).returnNew(true),
                    MongoSequence.class);
            return seq.getCollectionId();
        } catch (Exception e) {
            logger.error("{}->{}", cardId, e.getMessage());
            return -1;
        }
    }

    public int getNextNavigationId(String cardId) {
        try {
            MongoSequence seq = mongoTemplate.findAndModify(
                    new Query(Criteria.where("_id").is(cardId)),
                    new Update().inc("navigationId", 1),
                    FindAndModifyOptions.options().upsert(true).returnNew(true),
                    MongoSequence.class);
            System.out.println(seq.getNavigationId());
            return seq.getNavigationId();
        } catch (Exception e) {
            logger.error("{}->{}",cardId,e.getMessage());
            return -1;
        }
    }

    public int getNextSearchEngineId(String cardId) {
        try {
            MongoSequence seq = mongoTemplate.findAndModify(
                    new Query(Criteria.where("_id").is(cardId)),
                    new Update().inc("searchEngineId", 1),
                    FindAndModifyOptions.options().upsert(true).returnNew(true),
                    MongoSequence.class);
            return seq.getSearchEngineId();
        } catch (Exception e) {
            logger.error("{}->{}",cardId,e.getMessage());
            return -1;
        }
    }
}
