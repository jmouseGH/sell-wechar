package z.demo.exception;

import z.demo.enums.ResultEnum;

public class SellException  extends  RuntimeException {
    private         Integer     code ;



    public SellException(ResultEnum resultEnum ) {

        super( resultEnum.getMsg() );
        this.code = resultEnum.getCode() ;
    }
    public SellException(ResultEnum resultEnum ,String errorMsg ) {

        super( resultEnum.getMsg() + errorMsg );
        this.code = resultEnum.getCode() ;
    }

}
