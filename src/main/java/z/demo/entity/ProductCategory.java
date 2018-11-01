package z.demo.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity         //与数据库表进行映射的注释
@Data
@DynamicUpdate      //会动态修改, 如果实体类的某个属性是Null,则不会进行数据库更新
public class ProductCategory {
//    CREATE TABLE `product_category` (
//            `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类目表',
//            `category_name` varchar(64) NOT NULL COMMENT '类目名字',
//            `category_type` int(11) NOT NULL COMMENT '类目编号',
//            `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
//            `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
//    PRIMARY KEY (`category_id`)
//) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4

    @Id
    @GeneratedValue  // 自增id 用
    private         Integer         categoryId ;        //类目id

    private         String          categoryName ;      //类目名
    private         Integer         categoryType ;      //类目 的类型

    private         Date            createTime ;
    private         Date            updateTime ;


    @Override
    public String toString() {
        return "ProductCategory{\n\t" +
                "categoryId=" + categoryId +
                ",\n\t categoryName='" + categoryName + '\'' +
                ",\n\t categoryType=" + categoryType +
                ",\n\t createTime=" + createTime +
                ",\n\t updateTime=" + updateTime +
                "\n" + '}';
    }
}
