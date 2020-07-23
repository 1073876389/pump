package com.xuwx.pump.service;


import com.xuwx.pump.entity.User;

import java.util.List;

/**
 * Created by xuwx on 2018/7/31.
 */
public interface UserService {

    void save(User user);

    void delete(Long id);

    void update(User user);

    User get(Long id);

    List<User> findList();

    boolean checkUsername(String username);

    User userLogin(String username, String password);
}
