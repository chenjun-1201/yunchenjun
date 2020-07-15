package com.itcast.controller;

import com.itcast.domain.Syslog;
import com.itcast.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SyslogController {
    @Autowired
    private ISyslogService iSyslogService;

    @RequestMapping("/sysLog/findAll.do")
    public ModelAndView findall(){
        ModelAndView modelAndView=new ModelAndView();
        List<Syslog> list = iSyslogService.findall();
        modelAndView.addObject("sysLogs",list);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }

}
