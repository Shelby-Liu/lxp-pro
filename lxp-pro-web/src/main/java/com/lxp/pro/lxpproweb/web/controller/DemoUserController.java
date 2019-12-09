package com.lxp.pro.lxpproweb.web.controller;


import com.lxp.pro.lxpproweb.entity.DemoUser;
import com.lxp.pro.lxpproweb.service.DemoUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author plutosteven
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/demoUser")
public class DemoUserController {
    @Resource
    private DemoUserService demoUserService;


    @PostMapping("/save")
    public void save() {
        DemoUser demoUser = new DemoUser();
        //demoUser.setId("1234");
       // demoUser.setName("demo");
        //demoUser.setLocation("hebei");

        demoUserService.save(demoUser);
    }


    @GetMapping("/getUser")
    public List<DemoUser> list() {
        return demoUserService.list();
    }

}

