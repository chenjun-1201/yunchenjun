package com.itcast.service.impl;

import com.itcast.dao.IPermissionDao;
import com.itcast.domain.Permission;
import com.itcast.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Controller
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao iPermissionDao;
    @Override
    public List<Permission> findall() {
        List<Permission>list=iPermissionDao.findallpermission();
        return list;
    }

    @Override
    public void addpermisssion(Permission permission) {
        iPermissionDao.addPermission(permission);

    }

    @Override
    public Permission findpermissionbyid(String id) {
        Permission permission = iPermissionDao.findbyid(id);
        return permission;
    }
}
