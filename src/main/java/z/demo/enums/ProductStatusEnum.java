package z.demo.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP((byte)0,"在架") ,
    DOWN((byte)1,"下架")
    ;


    private Byte code ;

    private String msg ;

    ProductStatusEnum ( Byte code, String msg ){
        this.code  = code ;
        this.msg = msg ;
    }
}
