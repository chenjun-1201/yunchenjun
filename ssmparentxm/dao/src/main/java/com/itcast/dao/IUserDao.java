package com.itcast.dao;

import com.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results(
            {@Result(id =true,column = "id",property = "id"),
                    @Result(property = "username",column = "username"),
                    @Result(property = "email",column = "email"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "phoneNum",column = "phoneNum"),
                    @Result(property = "status",column = "status"),


//            @Result(property = "roleName",column = "roleName"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "permissions",column = "permissions"),
                    @Result(property = "roles",javaType = List.class,column = "id",
                            many =@Many(select = "com.itcast.dao.IRoleDao.findallrole",fetchType = FetchType.LAZY)),


            })
    public UserInfo findbyusername(String username);
    @Select("select * from users")
    @Results(
            {@Result(id =true,column = "id",property = "id"),
                    @Result(property = "username",column = "username"),
                    @Result(property = "email",column = "email"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "phoneNum",column = "phoneNum"),
                    @Result(property = "status",column = "status"),


//            @Result(property = "roleName",column = "roleName"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "permissions",column = "permissions"),
            @Result(property = "roles",javaType = List.class,column = "id",
                    many =@Many(select = "com.itcast.dao.IRoleDao.findrolebyid",fetchType = FetchType.LAZY)),


    })
    public List<UserInfo> findalluser();

    @Select(
            "select * from users where id=#{id}"
    )
    @Results({
            @Result(id =true,column = "id",property = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",javaType = List.class,column = "id",
                    many =@Many(select = "com.itcast.dao.IRoleDao.findrolebyid",fetchType = FetchType.LAZY)),})
    public UserInfo finduserbyid(String id);

//    @Insert("insert into users values(#{userInfo.id},#{userInfo.username}" +
//            ",#{userInfo.email},#{userInfo.password}," +
//            "#{userInfo.phoneNum},#{userInfo.status})")
@Insert("insert into users values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
public void adduserinfo(UserInfo UserInfo);
}
