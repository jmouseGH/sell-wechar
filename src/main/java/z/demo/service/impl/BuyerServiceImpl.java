package z.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderMaster;
import z.demo.service.BuyerService;
import z.demo.service.OrderMasterService;

@Slf4j
@Service
public class BuyerServiceImpl  implements BuyerService  {

    @Autowired
    OrderMasterService service ;


    @Override
    public OrderMasterDTO findOrderOne(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = service.findOne( orderId );
        if( orderMasterDTO == null ) throw new RuntimeException("输入参数错误");
        if( !openid.equals(orderMasterDTO.getBuyerOpenid() )){
            log.error("涉嫌越权的访问");
            throw new RuntimeException("涉嫌越权的访问");
        }


        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO cancelOrderOne(String openid, String orderId) {

        OrderMasterDTO orderMasterDTO = service.findOne( orderId );
        if( orderMasterDTO == null ) throw new RuntimeException("输入参数错误");
        if( !openid.equals(orderMasterDTO.getBuyerOpenid() )){
            log.error("涉嫌越权的访问");
            throw new RuntimeException("涉嫌越权的访问");
        }

        return service.cancel(orderMasterDTO);
    }
}
