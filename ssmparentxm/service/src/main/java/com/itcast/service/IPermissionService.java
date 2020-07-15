package com.itcast.service;

import com.itcast.domain.Permission;

import java.util.List;


public interface IPermissionService {
    public List<Permission>findall();
    public  void addpermisssion(Permission permission);
    public Permission findpermissionbyid(String id);
}
