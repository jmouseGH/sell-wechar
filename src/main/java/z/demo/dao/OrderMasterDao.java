package z.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import z.demo.entity.OrderMaster;

public interface OrderMasterDao extends JpaRepository<OrderMaster, String> {

    /**
     * 分页返回 某个买家的订单
     * @param buyerOpenid 某个用户 的微信id
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid (String buyerOpenid , Pageable pageable ) ;


}
