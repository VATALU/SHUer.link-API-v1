package org.shuerlink.client;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.shuerlink.model.entity.SHUHelperInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class ServiceClient  {
    private static final Logger logger =  LoggerFactory.getLogger(ServiceClient.class.getName());
    private static final String host = "http://services.shu.edu.cn/";

    public SHUHelperInfo login(String userName, String password) throws IOException {
        Connection.Response response = Jsoup.connect(host + "/login.aspx")
                .data("__EVENTTARGET", "")
                .data("__EVENTARGUMENT", "")
                .data("txtUserName", userName)
                .data("txtPassword", password)
                .data("btnOk", "提交(Submit)")
                .userAgent("Mozilla").timeout(1000 * 10).ignoreContentType(true).execute();

        String html = response.body();
        if (html.contains("用户名密码错误!") || html.contains("系统出错了") || html.contains("工号")) {
            logger.info(userName+" 登陆失败");
            return null;
        } else {
            logger.info(userName+" 登陆成功");
            SHUHelperInfo shuHelperInfo = new SHUHelperInfo();
            Map<String, String> cookies = response.cookies();
            getData(shuHelperInfo,cookies);
            return shuHelperInfo;
        }
    }

    public void getData(SHUHelperInfo shuHelperInfo, Map<String, String> cookies) throws IOException {
        Document document = Jsoup.connect(host + "/user/userPerInfo.aspx")
                .cookies(cookies)
                .timeout(1000)
                .userAgent("Mozilla")
                .get();
        String name = document.select("#userName").text();
        String nickname = document.select("#nickname").text();
        shuHelperInfo.setName(name);
        shuHelperInfo.setNickname(nickname);
    }

}
