[TOP]

# d s #

springboot2.0自动配置的DataSource是

HikariDataSource

HikariDataSource：是一个高性能的数据源

## 使用自己的DataSource步骤：

### 1：配置全局配置文件

```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/test
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    type: com.alibaba.druid.pool.DruidDataSource
#       配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    filters: stat,wall,log4j
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
```

原理：org.springframework.boot.autoconfigure.jdbc:

参考DataSourceConfiguration类，如下，可以根据spring.datasource.type修改自己的数据源

````java
 @Configuration(
        proxyBeanMethods = false
    )
    @ConditionalOnClass({HikariDataSource.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
        name = {"spring.datasource.type"},
        havingValue = "com.zaxxer.hikari.HikariDataSource",
        matchIfMissing = true
    )
````

这个类的源码还配置了其他的数据源如下：	

2、SpringBoot默认可以支持;
 1 org.apache.tomcat.jdbc.pool.DataSource、2HikariDataSource、

3BasicDataSource、



### 2.引入druid的依赖

```xml
 <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.0.9</version>
        </dependency>
```

### 3.编写配置类，引入Druid的servlet和filter

```java
@Configuration
public class Myconfig {
    @Bean
    public DataSource druid(){
        DataSource ds=new DruidDataSource();

        return ds;
    }

    @Bean
    public ServletRegistrationBean staticViewServlet(){
      // 引入Druid的StatViewServlet
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet());
        Map<String ,String>initParams=new HashMap<>();
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        // initParams.put("deny","192.168.15.21");
        servletRegistrationBean.setInitParameters(initParams);
        List<String> li = new ArrayList<>();
        li.add("/druid/*");
        servletRegistrationBean.setUrlMappings(li);
        return servletRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean staticViewFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//引入Druid的filter
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        List<String> li = new ArrayList<String>();
        li.add("/*");
        bean.setUrlPatterns(li);
        return bean;

    }

}
```



### 4访问http://8080/duid/1查看效果

