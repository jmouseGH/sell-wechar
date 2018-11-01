package z.demo.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.ProductCategory;

import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.Assert.*;



@RunWith(SpringRunner .class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    ProductCategoryDao dao ;

    @Test
    public void fineOne () {
        System.out.println( dao.findOne( 1 ) );
    }

    @Test
    public void findByCategoryTypeIn ( ){

        System.out.println( dao.findByCategoryTypeIn ( Arrays.asList( 1,2,3,4 )) );
    }


    @Test
    @Transactional // 测试 中的事务注释, 可以 回滚所有脏数据
    public void save () {
        ProductCategory productCategory = new ProductCategory() ;

        productCategory.setCategoryName("手机");
        productCategory.setCategoryType(5);


        Assert.assertNotNull( dao.save(productCategory) );
        System.out.println( dao.save(productCategory));
    }
}