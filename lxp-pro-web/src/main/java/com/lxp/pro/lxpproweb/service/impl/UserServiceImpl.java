package com.lxp.pro.lxpproweb.service.impl;

import com.lxp.pro.lxpproweb.entity.User;
import com.lxp.pro.lxpproweb.mapper.UserMapper;
import com.lxp.pro.lxpproweb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author plutosteven
 * @since 2019-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
