package com.itcast.dao;

import com.itcast.domain.Member;
import com.itcast.domain.Orders;
import com.itcast.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("iOrdersDao")
public interface IOrdersDao {
    @Select("Select * from orders ")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itcast.dao.IProductDao.findById")),
    })
    public List<Orders> findallorder();

    @Insert("insert into orders values(#{id},#{orderNum},#{orderTime},#{orderTimeStr},#{orderStatus}," +
            "#{peopleCount},#{product},#{travellers},#{member},#{payType},#{payTypeStr},#{orderDesc}")
    public void addOrders();

    @Select("select * from orders where id=#{id}")
    @Results(id = "orderresult",value = {
            @Result(id =true,column = "id",property = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "payTypeStr",column = "payTypeStr"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderTimeStr",column = "orderTimeStr"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.itcast.dao.IProductDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many =@Many(select = "com.itcast.dao.ITravelerDao.findtravellerbyid",fetchType = FetchType.LAZY)),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.itcast.dao.IMemberDao.findmemberbyid")),
            @Result(property = "orderStatusStr",column = "orderStatusStr")
    })
    public Orders findOrerbyid(String id);
}
