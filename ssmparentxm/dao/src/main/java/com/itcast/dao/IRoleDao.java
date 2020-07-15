package com.itcast.dao;

import com.itcast.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRoleDao {
    @Select("select * from role")
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property = "roleName" ,column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itcast.dao.IPermissionDao.findbyid",fetchType = FetchType.LAZY)),
            @Result(property = "users",column = "id" ,javaType = List.class,many =@Many(select = "com.itcast.dao.IUserDao.finduserbyid",fetchType = FetchType.LAZY) )

    })
    public List<Role> findallrole();


    @Select("select * from role where id in (select id from users_role where id=#{id})")
    @Results({
            @Result(id = true ,property = "id",column = "id"),
            @Result(property = "roleName" ,column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itcast.dao.IPermissionDao.findbyroleid",fetchType = FetchType.LAZY)),
            @Result(property = "users",column = "id" ,javaType = List.class,many =@Many(select = "com.itcast.dao.IUserDao.finduserbyid",fetchType = FetchType.LAZY) )

    })
    public  List<Role> findrolebyid(String id);

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    public  void addrole(Role role);



}
