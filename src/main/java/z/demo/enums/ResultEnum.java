package z.demo.enums;

import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
public enum  ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确") ,
    ORDER_NOT_EXIST( 12, "不存在相应 的订单 ") ,
    ORDER_DETAIL_NOT_EXIST ( 13, "不存在相关的子订单") ,

    ORDER_STATUS_WRONG( 14, "试图取消无法取消的订单 "),
    UPDATE_ORDER_STATUS_FAIL ( 15, "更新状态失败") ,

    UPDATE_PAY_STATUS_FAIL ( 16, "更新支付状态失败") ,

    PARAMER_ERROR(22, " 参数不正确 ")
    ;


    private     Integer     code ;
    private     String      msg ;

    ResultEnum (Integer code, String  msg ){
        this.code = code ;
        this.msg = msg ;
    }
}
