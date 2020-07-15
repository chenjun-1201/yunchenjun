package com.itcast.service.impl;

import com.itcast.dao.IUserDao;
import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itcast.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findbyusername(s);
        List<Role>list=userInfo.getRoles();
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),getsimpleGrantedAuthority(list));
        return user;
    }

    public List<SimpleGrantedAuthority> getsimpleGrantedAuthority(List<Role> roles){
        List<SimpleGrantedAuthority>list=new ArrayList<SimpleGrantedAuthority>();

        for (Role r:roles
             ) {
                list.add(new SimpleGrantedAuthority("ROLE_"+r.getRoleName()));
        }

        return list;
    }

    @Override
    public List<UserInfo> findall() {
        List<UserInfo> findalluser = userDao.findalluser();
        return findalluser;
    }


    @Override
    public UserInfo finduserbyid(String id) {
        UserInfo finduserbyid = userDao.finduserbyid(id);

        return finduserbyid;
    }

    @Override
    public void adduser(UserInfo userInfo) {

        bCryptPasswordEncoder.encode(userInfo.getPassword());
        userDao.adduserinfo(userInfo);
    }
}
