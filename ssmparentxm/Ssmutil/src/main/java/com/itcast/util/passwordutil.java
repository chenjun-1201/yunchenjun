package com.itcast.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordutil {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
       // BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String s = passwordutil.encodePassword("123456");
        System.out.println(s);
    }
}
