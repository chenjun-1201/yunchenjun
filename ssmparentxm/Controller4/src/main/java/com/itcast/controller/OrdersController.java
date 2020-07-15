package com.itcast.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.domain.Orders;
import com.itcast.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;
    @RequestMapping("orders/findAll.do")//用户先传一个page 和size参数
    public ModelAndView findallorders(@RequestParam(name = "page",required = true ,defaultValue = "1") int page,@RequestParam(name = "size",required = true ,defaultValue = "1") int size){
        List< Orders >list=ordersService.findallOrders(page,size);
        ModelAndView modelAndView=new ModelAndView();

        PageInfo<Orders>pageInfo=new PageInfo<Orders>(list);
        //像页面传一个pageinfo，
        modelAndView.addObject("ordersList", pageInfo);
        modelAndView.setViewName("orders-list");

        return modelAndView;
    }

    @RequestMapping("/orders/findById.do")
    public ModelAndView findById(String id){
        Orders orders= ordersService.findordersbyid(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("orders-show");
        modelAndView.addObject("orders",orders);
        //modelAndView.setView("");
        return modelAndView;
    }
}
