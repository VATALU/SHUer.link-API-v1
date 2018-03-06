package org.shuerlink.dao.daoImpl;

import org.shuerlink.dao.UserDao;
import org.shuerlink.model.dto.UserInfo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public UserInfo findUserById(String cardId) {
        return mongoTemplate.findOne(new Query(Criteria.where("cardId").is(cardId)),UserInfo.class);
    }

    @Override
    public int saveUserInfo(UserInfo userInfo) {
        mongoTemplate.insert(userInfo);
        return 1;
    }
}
