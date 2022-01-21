package org.lhq.controller;

import org.lhq.anno.ioc.AutoInject;
import org.lhq.anno.mvc.Controller;
import org.lhq.anno.mvc.RequestMapping;
import org.lhq.service.UserService;
@Controller
@RequestMapping("user/")
public class UserController {
    @AutoInject
    private UserService userService;

	@RequestMapping("hello")
    public String holle(){
    	return "你好";
	}



}
