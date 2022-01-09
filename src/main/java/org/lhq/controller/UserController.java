package org.lhq.controller;

import org.lhq.anno.AutoInject;
import org.lhq.service.UserService;

public class UserController {
    @AutoInject
    private UserService userService;
}
