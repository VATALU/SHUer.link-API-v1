package org.shuerlink.service;

import org.shuerlink.model.dto.UserInfo;

public interface SHULoginService {
    public UserInfo loginUser (String cardId, String password);
}
