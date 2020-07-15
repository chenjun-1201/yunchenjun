package com.itcast.dao;

import com.itcast.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {

    @Select("select * from `member` where id=#{id}")
    public Member findmemberbyid(String id);

}
