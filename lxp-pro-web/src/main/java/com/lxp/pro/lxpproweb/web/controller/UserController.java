package com.lxp.pro.lxpproweb.web.controller;


import com.lxp.pro.lxpproweb.entity.User;
import com.lxp.pro.lxpproweb.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author plutosteven
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/api/pro/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/demo")
    public String save(@RequestBody String loginInfo) {
        System.out.println(loginInfo);
        return "demo";
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }

}

