package z.demo.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT((byte)0,"未支付") ,
    SUCCESS((byte)1,"已支付")
    ;


    private Byte code ;

    private String msg ;

    PayStatusEnum ( Byte code, String msg ){
        this.code  = code ;
        this.msg = msg ;
    }
}
