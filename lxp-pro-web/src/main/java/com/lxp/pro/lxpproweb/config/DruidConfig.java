package com.lxp.pro.lxpproweb.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName  : DruidConfig
 * Author     : Draghu
 * Date       : Create in 2019/12/5
 * Description: TODO
 */
public class DruidConfig {

    /**
     * ServletRegistrationBean,
     * @see com.alibaba.druid.support.http.ResourceServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<Servlet> druid = new ServletRegistrationBean<>();
        druid.setServlet(new StatViewServlet());
        druid.setUrlMappings(Collections.singletonList("/druid/*"));

        Map<String, String> params = new HashMap<String, String>();
        params.put("loginUsername", "admin");
        params.put("loginPassword", "admin");
        druid.setInitParameters(params);
        return druid;
    }

    /**
     * @see WebStatFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> fitler = new FilterRegistrationBean<>();
        fitler.setFilter(new WebStatFilter());
        fitler.setUrlPatterns(Collections.singletonList("/*"));
        fitler.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return fitler;
    }
}
