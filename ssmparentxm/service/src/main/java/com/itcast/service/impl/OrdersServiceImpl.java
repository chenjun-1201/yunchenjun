package com.itcast.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.dao.IOrdersDao;
import com.itcast.domain.Orders;
import com.itcast.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements IOrdersService {
    @Qualifier("iOrdersDao")
    @Autowired
    private IOrdersDao ordersDao;
    @Override
    public List<Orders> findallOrders(int page,int size) {
        //调用分页方法，必须在find*r方法前，否则无效，会在下面添加一个sql语句
        PageHelper.startPage(page,size);
      List<Orders>list=  ordersDao.findallorder();
        return list;
    }

    @Override
    public Orders findordersbyid(String id) {
        return ordersDao.findOrerbyid(id);
    }




}
