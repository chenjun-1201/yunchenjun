package com.itcast.dao;

import com.itcast.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("iProductDao")
public interface IProductDao {
    @Select("select * from product ")
public List<Product>findall();

    @Insert("insert into product values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void addproduct(Product product);

    @Select("select * from product where id=#{id}")
    public Product findById(String id);
}
