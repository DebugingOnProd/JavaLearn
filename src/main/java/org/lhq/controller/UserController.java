package org.lhq.controller;

import org.lhq.anno.mvc.Controller;
import org.lhq.anno.mvc.RequestMapping;
@Controller
@RequestMapping("user/")
public class UserController {

	@RequestMapping("hello")
    public String holle(){
    	return "你好";
	}



}
