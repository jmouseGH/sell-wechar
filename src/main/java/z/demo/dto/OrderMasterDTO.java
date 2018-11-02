package z.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import z.demo.entity.OrderDetail;
import z.demo.enums.OrderStatusEnum;
import z.demo.enums.PayStatusEnum;
import z.demo.util.Date2LongSeriz;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 数据传输对象.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL ) // 不返回　Ｎull 值的字段
public class OrderMasterDTO {

    private     String      orderId ;
    private     String      buyerName ;
    private     String      buyerPhone ;
    private     String      buyerAddress ;
    private     String      buyerOpenid ;

    private     BigDecimal  orderAmount ;
    private     Byte        orderStatus         =   OrderStatusEnum.NEW.getCode();
    private     Byte        payStatus           =   PayStatusEnum.WAIT.getCode();

    @JsonSerialize(using = Date2LongSeriz.class ) // 将类使用转换
    private     Date        createTime ;
    @JsonSerialize(using = Date2LongSeriz.class )
    private     Date        updateTime ;


    private List<OrderDetail> orderDetailList ;



    ///////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "OrderMasterDTO{\n\t " +
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
                ",\n\t orderDetailList=" + orderDetailList +
                "\n" + '}';
    }
}
