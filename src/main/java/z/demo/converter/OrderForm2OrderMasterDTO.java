package z.demo.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderDetail;
import z.demo.enums.ResultEnum;
import z.demo.exception.SellException;
import z.demo.form.OrderForm;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class OrderForm2OrderMasterDTO {

    public static OrderMasterDTO convert (OrderForm orderForm ){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO() ;
        orderMasterDTO.setBuyerName( orderForm.getName() );
        orderMasterDTO.setBuyerPhone( orderForm.getPhone() );
        orderMasterDTO.setBuyerOpenid( orderForm.getOpenId() );
        orderMasterDTO.setBuyerAddress( orderForm.getAddress() );


        Gson gson = new Gson();
        List<OrderDetail> list = new ArrayList<>();
        try {
           list = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch ( Exception ex ){
            log.error("json对象转换失败.") ;
            throw new SellException(ResultEnum.PARAMER_ERROR);
        }


        orderMasterDTO.setOrderDetailList(list);
        return  orderMasterDTO ;
    }
}
