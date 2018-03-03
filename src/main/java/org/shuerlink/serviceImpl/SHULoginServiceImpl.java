package org.shuerlink.serviceImpl;

import org.shuerlink.client.SHUHelperClient;
import org.shuerlink.client.ServiceClient;
import org.shuerlink.daoImpl.SettingDaoImpl;
import org.shuerlink.daoImpl.UserDaoImpl;
import org.shuerlink.model.dto.UserInfo;
import org.shuerlink.model.entity.SHUHelperInfo;
import org.shuerlink.model.contains.CommonEnum;
import org.shuerlink.model.entity.JWTInfo;
import org.shuerlink.service.SHULoginService;
import org.shuerlink.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class SHULoginServiceImpl implements SHULoginService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ServiceClient serviceClient;
    @Resource
    private UserDaoImpl userDao;
    @Resource
    private SettingDaoImpl settingDao;
    @Resource
    private SHUHelperClient shuHelperClient;

    public UserInfo loginUser(String cardId, String password) {
        UserInfo userInfo = null;
        try {
            SHUHelperInfo shuHelperInfo = shuHelperClient.login(cardId, password);
            //登陆成功
            if (shuHelperInfo != null) {
                //查询用户的信息
                userInfo = userDao.findUserById(cardId);
                //判断用户是否已经储存过数据
                if (userInfo == null) {
                    userInfo = new UserInfo();
                    userInfo.setCardId(cardId);
                    if(userDao.saveUserInfo(userInfo)==1){
                        return null;
                    }
                }
                userInfo.setBySHUhelperInfo(shuHelperInfo);
                //生成token
                JWTInfo jwtInfo = new JWTInfo();
                jwtInfo.setCardId(cardId);
                String token = JwtUtil.sign(jwtInfo, Long.valueOf(CommonEnum.JWT_MAXAGE.getMsg()));
                userInfo.setToken(token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
