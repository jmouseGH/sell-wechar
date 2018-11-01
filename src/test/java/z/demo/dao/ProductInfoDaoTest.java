package z.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.ProductInfo;

import java.math.BigDecimal;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired ProductInfoDao dao ;

    @Test public void save () {
        ProductInfo productInfo = new ProductInfo() ;
        productInfo.setProductId("123456");
        productInfo.setCategoryType(3);
        productInfo.setProductName("原味丝袜");
        productInfo.setProductPrice( new BigDecimal( "333.33") );
        productInfo.setProductStatus( (byte)0 );
        productInfo.setProductStock( 100 );

        System.out.println( dao.save(productInfo) ) ;
    }

    @Test
    public void findByProductStatus() {
        System.out.println( dao.findByProductStatus( (byte)1 ));
        System.out.println( dao.findByProductStatus( (byte)0 ));
    }
}