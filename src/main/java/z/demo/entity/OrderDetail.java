package z.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /**
     * CREATE TABLE `order_detail` (
     *   `detail_id` varchar(32) NOT NULL,
     *   `order_id` varchar(32) NOT NULL,
     *   `product_id` varchar(32) NOT NULL,
     *   `product_name` varchar(64) NOT NULL COMMENT '商品名称',
     *   `product_price` decimal(8,2) unsigned NOT NULL COMMENT '当前价格,单位分',
     *   `product_quantity` int(11) unsigned NOT NULL COMMENT '数量',
     *   `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     *   PRIMARY KEY (`detail_id`),
     *   KEY `idx_order_id` (`order_id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
     */

    @Id
    private     String      detailId ;
    private     String      orderId ;
    private     String      productId   ;

    private     String      productName ;
    private BigDecimal      productPrice   ;
    private     Integer     productQuantity ;
    private     String      productIcon ;

    private     Date        createTime  ;
    private     Date        updateTime  ;

    @Override
    public String toString() {
        return "OrderDetail{\n\t " +
                "detailId='" + detailId + '\'' +
                ",\n\t orderId='" + orderId + '\'' +
                ",\n\t productId='" + productId + '\'' +
                ",\n\t productName='" + productName + '\'' +
                ",\n\t productPrice=" + productPrice +
                ",\n\t productQuentity=" + productQuantity +
                ",\n\t productIcon='" + productIcon + '\'' +
                ",\n\t createTime=" + createTime +
                ",\n\t updateTime=" + updateTime +
                "\n" + '}';
    }
}
