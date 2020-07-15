package com.itcast.service;

import com.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo>findall();
    public UserInfo finduserbyid(String id);
    public void adduser(UserInfo userInfo);
}
