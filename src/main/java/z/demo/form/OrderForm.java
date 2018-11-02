package z.demo.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message = "姓名, 必填")
    private     String      name ;

    @NotEmpty ( message = "电话,必填")
    private     String      phone ;

    @NotEmpty(message = "地址,必填 ")
    private     String      address ;

    @NotEmpty(message = "openid")
    private     String      openId ;

    @NotEmpty(message = "购买详情")
    private     String      items ;

}
