package z.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import z.demo.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDao extends JpaRepository<OrderDetail, String > {

    /**
     * 查询出某个订单的所有子订单
     * @param orderId
     * @return
     */
    List< OrderDetail > findByOrderId ( String orderId ) ;
}
