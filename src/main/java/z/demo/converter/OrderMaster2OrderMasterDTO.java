package z.demo.converter;

import org.springframework.beans.BeanUtils;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderMaster;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderMasterDTO {

    public static OrderMasterDTO orderMasterDTO(OrderMaster orderMaster ){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties( orderMaster,orderMasterDTO);


        return  orderMasterDTO;
    }

    public static List<OrderMasterDTO>  toOrderMasterDTOList ( List<OrderMaster> orderMasterList){

        return  orderMasterList.stream().map( e ->
                orderMasterDTO(e)).collect(Collectors.toList()) ;
    }


}
