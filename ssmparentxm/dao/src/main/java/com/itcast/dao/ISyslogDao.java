package com.itcast.dao;

import com.itcast.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISyslogDao {
    @Insert("insert into syslog values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void addsyslog(Syslog syslog);

    @Select("select * from syslog")
    public List<Syslog> findall();

}
