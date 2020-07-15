import com.itcast.domain.Product;
import com.itcast.service.IProductService;
import com.itcast.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class iii {
    @Test
    public void t(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext_service.xml");


        IProductService iProduc = (ProductServiceImpl) ac.getBean("productServiceImpl");

        List<Product> list = iProduc.findall();

        for (Product p:list
        ) {
            System.out.println(p.getCityName());

        }
    }
}
