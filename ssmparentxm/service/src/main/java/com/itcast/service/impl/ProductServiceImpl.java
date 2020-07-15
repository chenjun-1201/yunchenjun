package com.itcast.service.impl;

import com.itcast.dao.IProductDao;
import com.itcast.domain.Product;
import com.itcast.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service("productServiceImpl")
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;
    @Override
    public List<Product> findall() {
        List<Product>list= iProductDao.findall();
        return list;
    }

    @Override
    public void addproduct(Product product) {
        iProductDao.addproduct(product);

    }
}
