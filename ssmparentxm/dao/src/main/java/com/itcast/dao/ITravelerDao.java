package com.itcast.dao;

import com.itcast.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITravelerDao {
    @Select("select * from traveller where orderId=#{ordersId}=#{id}")
    public List<Traveller> findtravellerbyid(String id);
}
