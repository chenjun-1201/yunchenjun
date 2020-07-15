package com.itcast.controller;

import com.itcast.domain.Permission;
import com.itcast.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class PermissionController {
    @Autowired
  private   IPermissionService iPermissionService;

    @RequestMapping("/permission/findAll.do")
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView();
        List<Permission> list = iPermissionService.findall();
        modelAndView.setViewName("permission-list");
        modelAndView.addObject("permissionList",list);
        return  modelAndView;
    }

    @RequestMapping("/permission/save.do")
    public String add(Permission permission){

      //  System.out.println(permission.getPassword());
        String s = UUID.randomUUID().toString();
        StringBuffer stringBuffer=new StringBuffer(s);
        String s2= stringBuffer.substring(1,25);
        permission.setId(s2);
        iPermissionService.addpermisssion(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/permission/findById.do")
    public ModelAndView findpermissionbyid(String id){
        ModelAndView modelAndView=new ModelAndView();
        Permission permission = iPermissionService.findpermissionbyid(id);
        modelAndView.setViewName("permission-show");
        modelAndView.addObject("permission",permission);
        return  modelAndView;
    }

}
