package com.lxp.pro.lxpproweb.config;

import com.alibaba.fastjson.JSONObject;
import com.lxp.pro.lxpproweb.util.UUIDWorkerInit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Order(0)
public class VueGlobalExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(VueGlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("code", "error");
            int status;
            if (e instanceof DuplicateKeyException) {
                status = 501;
                result.put("status", status);
                result.put("message", "请勿频繁操作");
                out.append(result.toJSONString());
                logger.error("status code: " + status + "，重复错误：", e);

            }  else {
                status = HttpStatus.INTERNAL_SERVER_ERROR.value();
                result.put("status", status);
                String randomCode = UUIDWorkerInit.getUUID();
                String message = "服务器出现错误，错误编号：" + randomCode + "，请联系管理员！";
                if (StringUtils.isNotEmpty(e.getMessage())) {
                    result.put("message", message);
                    out.append(result.toJSONString());
                    logger.error("status code: 500，编号：" + randomCode + "，未知错误：", e);
                } else {
                    result.put("message", message);
                    out.append(result.toJSONString());
                    logger.error("status code: 500，编号：" + randomCode + "，未知错误：", e);
                }
            }
            response.setStatus(status);
            response.setHeader("Access-Control-Allow-Origin","*");


        } catch (IOException e1) {
            logger.error("全局错误：", e1);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

        return new ModelAndView();
        // 根据不同错误转向不同页面
    }
}
