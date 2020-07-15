package com.itcast.controller;

import com.itcast.domain.Role;
import com.itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/role/findAll.do")
    public ModelAndView findallrole(){
        ModelAndView modelAndView=new ModelAndView();
        List<Role> roles = roleService.findallrole();
        modelAndView.addObject("roleList",roles);
        modelAndView.setViewName("role-list");
        return  modelAndView;
    }
    @RequestMapping("/role/save.do")
    public String addrole(Role role){

        String s = UUID.randomUUID().toString();
        StringBuffer stringBuffer=new StringBuffer(s);
        String s2= stringBuffer.substring(1,25);
        role.setId(s2);
        roleService.addrole(role);
        return "redirect:findAll.do";
    }
}
