package com.itcast.service;

import com.itcast.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findall();
    public  void addproduct(Product product);
}
