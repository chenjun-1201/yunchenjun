package com.itcast.controller;

import com.itcast.domain.UserInfo;
import com.itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@Controller

public class UserController {
    @Autowired
    private IUserService userService;
    @RolesAllowed("ADMIN")
    @RequestMapping("/user/findalluser.do")
    public ModelAndView findalluser(){

        ModelAndView modelAndView=new ModelAndView();
        List<UserInfo> list = userService.findall();
        modelAndView.setViewName("user-list");
       modelAndView.addObject("userList",list);
        return  modelAndView;
    }
    @RequestMapping("user/findById.do")
public  ModelAndView finduserbyid(String id){
       UserInfo userInfo= userService.finduserbyid(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show");
        return  modelAndView;
}
@RequestMapping("user/add.do")
public String  adduser(UserInfo userInfo){
    System.out.println(userInfo.getPassword());
    String s = UUID.randomUUID().toString();
    StringBuffer stringBuffer=new StringBuffer(s);
   String s2= stringBuffer.substring(1,25);
    userInfo.setId(s2);
userService.adduser(userInfo);


        return "redirect:findalluser.do";
}
}
