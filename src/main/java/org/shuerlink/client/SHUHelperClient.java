package org.shuerlink.client;

import com.alibaba.fastjson.JSON;
import org.shuerlink.model.entity.SHUHelperInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

@Repository
public class SHUHelperClient {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final String HOST = "https://www.shuhelper.cn";

    public SHUHelperInfo login(String userName, String password) throws IOException {
        String url = HOST + "/api/users/login/";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) ...");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(String.format("{\"card_id\":\"%s\",\"password\":\"%s\"}", userName, password));
        wr.flush();
        wr.close();
        String responseBody = readResponseBody(con.getInputStream());
        return JSON.parseObject(responseBody,SHUHelperInfo.class);
    }

    private String readResponseBody(InputStream inputStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
