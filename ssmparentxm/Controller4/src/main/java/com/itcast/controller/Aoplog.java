package com.itcast.controller;

import com.itcast.domain.Syslog;
import com.itcast.service.ISyslogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component
@Aspect
public class Aoplog {

@Autowired
private ISyslogService iSyslogService;
    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    //
    private Method executionMethod; // 访问的方法 // 主要获取访问时间、访问的类、访问的方法
    @Autowired
    private HttpServletRequest request;

//    @Before("execution(* com.itcast.controller.*.*(..))")
    @Before("execution(* com.itcast.controller.*.*(..))")
    public void dobefore(JoinPoint jp) throws NoSuchMethodException {
        //获取当前时间
    startTime=new Date();
    Object[] args=jp.getArgs();
        executionClass= jp.getTarget().getClass();
        //获取方法名称
        String methodname=jp.getSignature().getName();
        if(args==null||args.length==0){
            executionMethod=executionClass.getMethod(methodname);

        }else {
            Class [] clazz  =new Class[args.length];
            for(int i=0;i<args.length;i++){
                clazz[i]=args.getClass();

            }
           executionMethod= executionClass.getMethod(methodname,clazz);

        }

    }

        @After("execution(* com.itcast.controller.*.*(..))")
    public void after(){
            // 主要获取日志中其它信息，时长、ip、url...

            long time = new Date().getTime()-startTime.getTime();
    //获取注解中的url
         if(executionClass!=null&&executionMethod!=null&&executionClass!=Aoplog.class){
             //1.获取类上的@RequestMapping("/orders")
             RequestMapping classannotion= (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
             String[] params=new String[1];
             if(classannotion!=null){
                 //获取类的@requestmapping的参数
                  params = classannotion.value();
//
                 if(executionMethod!=null){
                     //获取方法上的注解@RequestMapping(xxx)
                     RequestMapping requestMapping=executionMethod.getAnnotation(RequestMapping.class);
                     // RequestMapping requestMapping= annotation;

                     if(requestMapping!=null){
                         String[] value = requestMapping.value();
                         String url=params[0]+value[0];

                         //获取访问的ip
                         String ip = request.getRemoteAddr();

                         //获取当前操作的用户
                         //获取当前操作的用户
                         SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                         User user = (User) context.getAuthentication().getPrincipal();
                         String username = user.getUsername();


                         //将日志相关信息封装到Syslog对象
                         String s = UUID.randomUUID().toString();
                         StringBuffer stringBuffer=new StringBuffer(s);
                         String s2= stringBuffer.substring(1,25);
                         Syslog Syslog = new Syslog();
                         Syslog.setExecutionTime(time); //执行时长
                         Syslog.setIp(ip);
                         Syslog.setMethod("[类名] " + executionClass.getName() + "[方法名] " + executionMethod.getName());
                         Syslog.setUrl(url);
                         Syslog.setUsername(username);
                         Syslog.setVisitTime(startTime);
                         Syslog.setId(s2);
                         iSyslogService.save(Syslog);

                     }
                 }
             }else {
                 params[0]="";
                 if(executionMethod!=null){
                     //获取方法上的注解@RequestMapping(xxx)
                     RequestMapping requestMapping=executionMethod.getAnnotation(RequestMapping.class);
                     // RequestMapping requestMapping= annotation;

                     if(requestMapping!=null){
                         String[] value = requestMapping.value();
                         String url=value[0];

                         //获取访问的ip
                         String ip = request.getRemoteAddr();

                         //获取当前操作的用户
                         //获取当前操作的用户
                         SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                         User user = (User) context.getAuthentication().getPrincipal();
                         String username = user.getUsername();


                         //将日志相关信息封装到Syslog对象
                         String s = UUID.randomUUID().toString();
                         StringBuffer stringBuffer=new StringBuffer(s);
                         String s2= stringBuffer.substring(1,25);
                         Syslog Syslog = new Syslog();
                         Syslog.setExecutionTime(time); //执行时长
                         Syslog.setIp(ip);
                         Syslog.setMethod("[类名] " + executionClass.getName() + "[方法名] " + executionMethod.getName());
                         Syslog.setUrl(url);
                         Syslog.setUsername(username);
                         Syslog.setVisitTime(startTime);
                         Syslog.setId(s2);
                         iSyslogService.save(Syslog);

                     }
                 }

             }


             
         }
        }


}
