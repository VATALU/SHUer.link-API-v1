package org.shuerlink.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.shuerlink.model.dto.UserInfo;
import org.shuerlink.model.request.LoginRequest;
import org.shuerlink.service.serviceImpl.SHULoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping(value = "/v1/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SHULoginServiceImpl loginService;

    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public ResponseEntity<UserInfo> login(@RequestBody LoginRequest loginRequest) {
        String cardId = loginRequest.getCardId();
        String password = loginRequest.getPassword();
        UserInfo userInfo = loginService.loginUser(cardId, password);
        if (userInfo != null) {
            log.info("{}->登录成功", cardId);
            return new ResponseEntity<UserInfo>(userInfo, HttpStatus.CREATED);
        } else {
            log.info("{}->登陆失败", cardId);
            return new ResponseEntity<UserInfo>(HttpStatus.NOT_FOUND);
        }
    }
}
