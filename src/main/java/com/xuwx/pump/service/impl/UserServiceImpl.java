package com.xuwx.pump.service.impl;


import com.xuwx.pump.entity.User;
import com.xuwx.pump.mbg.mapper.UserMapper;
import com.xuwx.pump.service.UserService;
import com.xuwx.pump.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuwx on 2018/7/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.get(id);
    }

    @Override
    public List<User> findList() {
        List<User> list = userMapper.findList();
        return list;
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }

    @Override
    public User userLogin(String username, String password) {
        User user =userMapper.userLogin(username,password);
        UserContext.setCurrent(user);
        return user ;
    }
}
