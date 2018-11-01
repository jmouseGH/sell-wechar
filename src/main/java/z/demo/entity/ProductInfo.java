package z.demo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情
 * 后面可能会再把 id 字段改加 long 类型
 */
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    /**
     * CREATE TABLE `product_info` (
     *   `product_id` varchar(32) NOT NULL,
     *   `product_name` varchar(64) NOT NULL COMMENT '商品名称',
     *   `product_price` decimal(8,2) unsigned NOT NULL COMMENT '单价',
     *   `product_stock` int(11) unsigned NOT NULL COMMENT '库存',
     *   `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
     *   `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
     *   `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
     *   `category_type` int(11) NOT NULL COMMENT '类目编号',
     *   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     *   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     *   PRIMARY KEY (`product_id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
     */
    @Id
    private     String      productId ;
    private     String      productName ;
    private     BigDecimal  productPrice ;
    private     Integer     productStock ;
    private     String      productDescription ;
    private     String      productIcon ;
    private     Byte        productStatus ;
    private     Integer     categoryType ;
    private     Date        createTime ;
    private     Date        updateTime  ;

    @Override
    public String toString() {
        return "ProductInfo{\n\t " +
                "productId='" + productId + '\'' +
                ",\n\t productName='" + productName + '\'' +
                ",\n\t productPrice=" + productPrice +
                ",\n\t productStock=" + productStock +
                ",\n\t productDescription='" + productDescription + '\'' +
                ",\n\t productIcon='" + productIcon + '\'' +
                ",\n\t productStatus=" + productStatus +
                ",\n\t categoryType=" + categoryType +
                ",\n\t createTime=" + createTime +
                ",\n\t updateTime=" + updateTime +
                "\n" + '}';
    }
}
