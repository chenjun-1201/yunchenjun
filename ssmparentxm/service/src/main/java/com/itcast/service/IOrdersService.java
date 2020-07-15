package com.itcast.service;

import com.itcast.domain.Orders;

import java.util.List;

public interface IOrdersService {
    public List<Orders> findallOrders(int page,int size);
    public Orders findordersbyid(String id);
}
