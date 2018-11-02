package z.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.OrderDetail;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao dao ;
    @Test
    public void findByOrderId() {
        System.out.println( dao.findByOrderId( "order_00001") );
    }
    @Test
    public void save () {
        OrderDetail orderDetail = new OrderDetail() ;
        orderDetail.setDetailId("order_detail_00001");
        orderDetail.setOrderId("order_00002");
        orderDetail.setProductIcon("xxxxxx");
        orderDetail.setProductId("product");
        orderDetail.setProductName("原味奶茶");
        orderDetail.setProductQuantity(50);
        orderDetail.setProductPrice( new BigDecimal("33.11"));

        System.out.println( dao.save( orderDetail ));
    }
}