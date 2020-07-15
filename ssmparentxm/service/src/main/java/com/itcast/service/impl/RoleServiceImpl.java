package com.itcast.service.impl;

import com.itcast.dao.IRoleDao;
import com.itcast.domain.Role;
import com.itcast.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;
    @Override
    public List<Role> findallrole() {
     List<Role>list=  iRoleDao.findallrole();
        return list;
    }

    public void addrole(Role role){
        iRoleDao.addrole(role);

    }
}
