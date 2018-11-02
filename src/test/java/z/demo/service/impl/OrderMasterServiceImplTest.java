package z.demo.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderDetail;
import z.demo.exception.SellException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired OrderMasterServiceImpl service ;

    public static final String buyerOpenid = " openid-00001 " ;

    @Test
    public void create() {

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO() ;
        orderMasterDTO.setBuyerAddress("北京一环");
        orderMasterDTO.setBuyerName("习**");
        orderMasterDTO.setBuyerOpenid( buyerOpenid );
        orderMasterDTO.setBuyerPhone("13888888888");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(3);
        orderDetailList.add(orderDetail);

        orderMasterDTO.setOrderDetailList( orderDetailList );

        service.create( orderMasterDTO );
    }

    @Test
    public void createStockError() {

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO() ;
        orderMasterDTO.setBuyerAddress("北京一环");
        orderMasterDTO.setBuyerName("习**");
        orderMasterDTO.setBuyerOpenid( buyerOpenid );
        orderMasterDTO.setBuyerPhone("13888888888");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1");
        orderDetail.setProductQuantity(800); // it's more than stock
        orderDetailList.add(orderDetail);

        orderMasterDTO.setOrderDetailList( orderDetailList );

            service.create( orderMasterDTO );
        

    }
    @Test
    public void findOne() {
        System.out.println( service.findOne("1541133482486249842"));
    }

    @Test
    public void findList() {
        System.out.println( service.findList(buyerOpenid ,new PageRequest( 0,1)));
    }

    @Test
    public void cancel() {
        OrderMasterDTO orderMasterDTO = service.findOne("1541145396962974601") ;

        OrderMasterDTO o =service.cancel( orderMasterDTO );

        System.out.println( o );
    }

    @Test
    public void finish() {
        OrderMasterDTO orderMasterDTO = service.findOne("3") ;

        OrderMasterDTO o =service.finish( orderMasterDTO );

        System.out.println( o );
    }

    @Test
    public void paid() {
        OrderMasterDTO orderMasterDTO = service.findOne("1541146225973549922 ") ;

        OrderMasterDTO o =service.paid( orderMasterDTO );

        System.out.println( o );
    }
}