package z.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.ProductInfo;
import z.demo.service.ProductInfoService;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl service ;
    @Test
    public void findOne() {
        System.out.println( service.findOne("123456"));
    }

    @Test
    public void findUp() {
        System.out.println( service.findUp());
    }

    @Test
    public void findAll() {
        System.out.println("============第3页=============");

        System.out.println( service.findAll(
                new PageRequest( 3,3)
        ));
        System.out.println("============第2页=============");

        System.out.println( service.findAll(
                new PageRequest( 2,3)
        ));
        System.out.println("============第1页=============");

        System.out.println( service.findAll(
                new PageRequest( 1,3)
        ));
        System.out.println("============第0页=============");
        System.out.println( service.findAll(
                new PageRequest( 0,3)
        ) .iterator().next()

        );
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo() ;
        productInfo.setProductId("1234567");
        productInfo.setCategoryType(3);
        productInfo.setProductName("原味胖次");
        productInfo.setProductPrice( new BigDecimal( "433.33") );
        productInfo.setProductStatus( (byte)0 );
        productInfo.setProductStock( 100 );

        System.out.println( service.save(productInfo) ) ;
    }
}