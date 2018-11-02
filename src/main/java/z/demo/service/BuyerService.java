package z.demo.service;

import z.demo.dto.OrderMasterDTO;

public interface BuyerService {

    // select a order

    OrderMasterDTO findOrderOne ( String openid, String orderId ) ;
    // cancel a order
    OrderMasterDTO cancelOrderOne ( String openid, String orderId ) ;

}
