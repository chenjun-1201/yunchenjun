package com.itcast.dao;

import com.itcast.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {

    @Select("select * from permission where id =#{id}) ")
    @Results(
            {@Result(id = true ,column = "id",property = "id"),
            @Result(property = "url",column = "url"),
                    @Result(property = "permissionName",column = "permissionName"),
                    @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itcast.dao.IRoleDao.findrolebyid",fetchType = FetchType.LAZY))
            }
    )
    public Permission findbyid(String id);

    @Select("select * from permission")
    public List<Permission> findallpermission();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    public  void addPermission(Permission permission);

//    @Select("select * from permission where id in (select id from role_permission where id=#{id}) ")
//    public List<Permission>findpermissionbyroleid(String id);


    //根据permission id查角色
    @Select("select * from permission where id in (select id from role_permission where id=#{id}) ")
    @Results(
            {@Result(id = true ,column = "id",property = "id"),
                    @Result(property = "url",column = "url"),
                    @Result(property = "permissionName",column = "permissionName"),
                    @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.itcast.dao.IRoleDao.findrolebyid",fetchType = FetchType.LAZY))
            }
    )
    public  List<Permission>  findbyroleid(String id);
}
