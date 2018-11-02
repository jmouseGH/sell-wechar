package z.demo.enums;

import lombok.Getter;
import org.hibernate.criterion.Order;


/**
 * 订单状态类
 */
@Getter
public enum OrderStatusEnum {


    NEW((byte)0,"新订单"),

    FINISH((byte)1,"完结"),

    CANCEL((byte)2,"已取消") ,
    ;


    private Byte code;

    private String msg;

    OrderStatusEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
