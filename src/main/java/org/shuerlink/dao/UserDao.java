package org.shuerlink.dao;

import org.shuerlink.model.dto.UserInfo;

public interface UserDao {
    public UserInfo findUserById(String cardId);
    public int saveUserInfo(UserInfo userInfo);
}
