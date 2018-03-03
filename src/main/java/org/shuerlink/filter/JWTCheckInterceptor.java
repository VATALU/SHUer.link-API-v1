package org.shuerlink.filter;

import com.alibaba.fastjson.JSON;
import org.shuerlink.model.entity.JWTInfo;
import org.shuerlink.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JWTCheckInterceptor implements HandlerInterceptor {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String jwt = request.getHeader("Authorization");
        String name = request.getHeader("name");
        LOGGER.info("JWTCheckInterceptor - jwt:{},name:{}", jwt, name);
        if ("".equals(jwt) || "".equals(name) || null == jwt || null == name) {
            responseMessage(response, response.getWriter(), 401);
            return false;
        }
        // 解密信息
        JWTInfo jwtInfo = JwtUtil.unsign(jwt, JWTInfo.class);
        LOGGER.info("jwt解密之后:{}", JSON.toJSONString(jwtInfo));
        if (jwtInfo == null) {
            responseMessage(response, response.getWriter(), 401);
            return false;
        }
        if (name.equals(jwtInfo.getCardId())) {
            return true;
        } else {
            responseMessage(response, response.getWriter(), 401);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, int status) {
        response.setStatus(status);
        out.flush();
        out.close();
    }
}
