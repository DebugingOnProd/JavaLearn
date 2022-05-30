package org.lhq.service.impl;

import org.lhq.entity.User;
import org.lhq.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        User user = new User();
        user.setName("nbu");
        user.setNickname("asdasd");
        return user;
    }
}
