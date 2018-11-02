package z.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import z.demo.enums.OrderStatusEnum;
import z.demo.enums.PayStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
/**
 * Create Table                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |
 * +--------------+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
 * | order_master | CREATE TABLE `order_master` (
 *   `order_id` varchar(32) NOT NULL,
 *   `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
 *   `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
 *   `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
 *   `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
 *   `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
 *   `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
 *   `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
 *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 *   PRIMARY KEY (`order_id`),
 *   KEY `idx_buyer_openid` (`buyer_openid`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
 */

    @Id
    private     String      orderId ;
    private     String      buyerName ;
    private     String      buyerPhone ;
    private     String      buyerAddress ;
    private     String      buyerOpenid ;

    private BigDecimal      orderAmount ;
    private     Byte        orderStatus         =   OrderStatusEnum.NEW.getCode();
    private     Byte        payStatus           =   PayStatusEnum.WAIT.getCode();

    private     Date        createTime ;
    private     Date        updateTime ;
//
//    @Transient // 因数据库中没有对应字段, 加注解以防止 出现异常
//    private List<OrderDetail> orderDetailList ;

    @Override
    public String toString() {
        return "OrderMaster{\n\t " +
                "orderId='" + orderId + '\'' +
                ",\n\t buyerName='" + buyerName + '\'' +
                ",\n\t buyerPhone='" + buyerPhone + '\'' +
                ",\n\t buyerAddress='" + buyerAddress + '\'' +
                ",\n\t buyerOpenid='" + buyerOpenid + '\'' +
                ",\n\t orderAmount=" + orderAmount +
                ",\n\t orderStatus=" + orderStatus +
                ",\n\t payStatus=" + payStatus +
                ",\n\t createTime=" + createTime +
                ",\n\t updateTime=" + updateTime +
                "\n" + '}';
    }
}
