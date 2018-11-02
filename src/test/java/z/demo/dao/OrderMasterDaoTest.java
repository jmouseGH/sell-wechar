package z.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.entity.OrderMaster;
import z.demo.enums.OrderStatusEnum;

import javax.print.attribute.standard.PageRanges;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    OrderMasterDao dao ;
    @Test
    public void findByBuyerOpenid() {

        System.out.println( dao.findByBuyerOpenid("2424234234" , new PageRequest( 0,1) ) .iterator().next());
    }

    @Test
    public void save () {

        OrderMaster orderMaster = new OrderMaster() ;
        orderMaster.setBuyerAddress(" ");
        orderMaster.setBuyerName("......");
        orderMaster.setBuyerOpenid("2424234234");
        orderMaster.setBuyerPhone("120");
        orderMaster.setOrderAmount( new BigDecimal("333.22") ) ;
        orderMaster.setOrderId("order_00001");

        System.out.println(  dao.save( orderMaster ) ) ;

    }
}