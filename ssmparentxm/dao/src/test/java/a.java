import com.itcast.dao.IOrdersDao;
import com.itcast.dao.IUserDao;
import com.itcast.domain.Orders;
import com.itcast.domain.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class a {
  //  @Test
//    public void aVoid(){
//       // InputStream in=ClassLoader.getSystemResourceAsStream("applicationContext_dao.xml");
//        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext_dao.xml");
//
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
//        SqlSessionFactory sqlSessionFactory= (SqlSessionFactory) ac.getBean("sqlSessionFactory");
//      SqlSession sqlSession= sqlSessionFactory.openSession();
//        IProductDao iProductDao = (IProductDao) ac.getBean("iOrdersDao");
//        List<Product> list = iProductDao.findall();
//
//        for (Product p:list
//             ) {
//            System.out.println(p.getCityName());
//
//        }
//
//            Product product=new Product();
//        product.setProductNum("itcast-004");
//        product.setId("60ed7a48-cc76-4d68-85cb-d89d9e981565");
//        product.setDepartureTime(null);
//        iProductDao.addproduct(product);
//
//    }

    @Test
    public  void findallord(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext_dao.xml");

        IOrdersDao iProductDao = (IOrdersDao) ac.getBean("iOrdersDao");
        List<Orders> list = iProductDao.findallorder();
        for (Orders o:list
             ) {
            System.out.println(o.getOrderDesc());
            System.out.println(o.getProduct().getCityName());

        }

    }
    @Autowired
    private IUserDao iUserDao;

    @Test
    public void j(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext_dao.xml");

        IUserDao iProductDao = (IUserDao) ac.getBean("iUserDao");
       // List<Orders> list = iProductDao.findallorder();
        //ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext_dao.xml");
        UserInfo u=new UserInfo();
        u.setEmail("dd");
        u.setPassword("dsfsd");
        u.setPhoneNum("dsdf");
        u.setUsername("sdfsdfsd");
        u.setStatus(1);
u.setRoles(null);

        u.setId("dsfksl");
        iProductDao.adduserinfo(u);

    }
}
