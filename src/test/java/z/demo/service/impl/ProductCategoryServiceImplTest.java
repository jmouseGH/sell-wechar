package z.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.ProductCategory;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class )
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired ProductCategoryServiceImpl service ;

    @Test
    public void findOne() {

        System.out.println( service.findOne( 11 ));
    }

    @Test
    public void findAll() {
        System.out.println( service.findAll() );
    }

    @Test
    public void findByCategoryTypeIn() {
        System.out.println( service.findByCategoryTypeIn(Arrays.asList( 1, 3, 4 )));
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory() ;
        productCategory.setCategoryName("top100");
        productCategory.setCategoryType(10);

        System.out.println( service.save( productCategory ));
    }
}