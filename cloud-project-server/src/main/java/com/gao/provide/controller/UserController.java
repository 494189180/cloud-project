package com.gao.provide.controller;

import com.gao.mapper.UserMapper;
import com.gao.model.User;
import com.gao.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/saveUser")
    public List<User> ConsuleUser(@RequestBody Map<String,Object> map) throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        BeanUtils.populate(user,map);
        int insert = userMapper.insert(user);
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user);
        return list;
    }
}
