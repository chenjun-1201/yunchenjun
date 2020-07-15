package com.itcast.controller;

import com.itcast.domain.Product;
import com.itcast.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private IProductService productService;
    @RequestMapping("product/findallproduct.do")
    public  ModelAndView findallproduct(){
       List<Product>list= productService.findall();

        for (Product p:list
        ) {
            System.out.println(p.getCityName());

        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("product_list");
       return modelAndView;
    }

    @RequestMapping("product/addproduct.do")
    public  String addproduct(Product product){
        UUID uuid=UUID.randomUUID();
        String uuu=uuid.toString();
        product.setId(uuu);
        productService.addproduct(product);

        return "redirect:findallproduct.do";
    }
}
