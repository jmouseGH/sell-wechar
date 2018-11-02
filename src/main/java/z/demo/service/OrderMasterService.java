package z.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import z.demo.dao.OrderMasterDao;
import z.demo.dto.OrderMasterDTO;

public interface OrderMasterService {

    OrderMasterDTO create ( OrderMasterDTO orderMasterDTO );

    OrderMasterDTO findOne ( String  orderId );

    Page<OrderMasterDTO> findList (String buyerOpenid , Pageable pageable );

    OrderMasterDTO cancel ( OrderMasterDTO orderMasterDTO );

    OrderMasterDTO finish ( OrderMasterDTO orderMasterDTO );

    OrderMasterDTO paid ( OrderMasterDTO orderMasterDTO ) ;

}
