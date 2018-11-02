package z.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import z.demo.converter.OrderMaster2OrderMasterDTO;
import z.demo.dao.OrderDetailDao;
import z.demo.dao.OrderMasterDao;
import z.demo.dto.CartDTO;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderDetail;
import z.demo.entity.OrderMaster;
import z.demo.entity.ProductInfo;
import z.demo.enums.OrderStatusEnum;
import z.demo.enums.PayStatusEnum;
import z.demo.enums.ResultEnum;
import z.demo.exception.SellException;
import z.demo.service.OrderMasterService;
import z.demo.service.ProductInfoService;
import z.demo.util.KeyUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    OrderMasterDao dao ;

    @Autowired
    OrderDetailDao detailDao ;

    @Autowired
    ProductInfoService productInfoService ;

    @Override
    @Transactional // 事务
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        // 查询商品, 数量价格
        String orderId = KeyUtil.getUniqueKey() ;

        BigDecimal orderAmount = new BigDecimal("0.00");

        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne( orderDetail.getProductId() );
            if(productInfo == null ){
                throw new SellException( ResultEnum.PRODUCT_NOT_EXIST ) ;
            }

            // 计算总价
            orderAmount = orderAmount.add(
                 productInfo.getProductPrice().multiply( new BigDecimal(orderDetail.getProductQuantity()) ) ) ;


            // 插入子订单
            orderDetail.setOrderId( orderId );
            orderDetail.setDetailId( KeyUtil.getUniqueKey() );
            BeanUtils.copyProperties( productInfo, orderDetail );

            detailDao.save( orderDetail ) ;

        }


        // 插入 主 订单
        OrderMaster orderMaster = new OrderMaster() ;
        BeanUtils.copyProperties( orderMasterDTO , orderMaster );
        orderMaster.setOrderId( orderId );
        orderMaster.setOrderAmount( orderAmount );

        orderMaster = dao.save( orderMaster ) ;

        // 扣库存
        List<CartDTO> cartDTOS ;
        cartDTOS = orderMasterDTO.getOrderDetailList().stream()
                .map( e -> new CartDTO( e.getProductId() , e.getProductQuantity() ))
                .collect(Collectors.toList());

        productInfoService.decreaseStock( cartDTOS );


        OrderMasterDTO result = this.findOne( orderId );

        return result  ;
    }

    @Override
    public OrderMasterDTO findOne(String orderId) {
         OrderMaster orderMaster = dao.findOne(orderId) ;
         if(orderMaster == null ) throw new SellException( ResultEnum.ORDER_NOT_EXIST ) ;
         List<OrderDetail> orderDetails = detailDao.findByOrderId(orderId);
         if(orderDetails == null ) throw new SellException( ResultEnum.ORDER_DETAIL_NOT_EXIST ) ;

         OrderMasterDTO orderMasterDTO = new OrderMasterDTO() ;
         BeanUtils.copyProperties( orderMaster ,orderMasterDTO );
         orderMasterDTO.setOrderDetailList( orderDetails );

         return orderMasterDTO ;
    }

    @Override
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = dao.findByBuyerOpenid( buyerOpenid , pageable );

        Page<OrderMasterDTO> orderMasterDTOPage = new PageImpl<OrderMasterDTO>(OrderMaster2OrderMasterDTO.toOrderMasterDTOList(orderMasterPage.getContent()) , pageable ,orderMasterPage.getTotalElements());

        return orderMasterDTOPage ;
    }

    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {


        // 判断订单状态, NEW 则可以取消
        OrderMaster orderMaster = dao.findOne( orderMasterDTO.getOrderId() );
        if( orderMaster == null    )     throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        if( orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            log.error("试图取消已完结或已取消的订单");
            throw new SellException( ResultEnum.ORDER_STATUS_WRONG );
        }
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        OrderMaster om = dao.save( orderMaster );
        if ( om.getOrderStatus() != OrderStatusEnum.CANCEL.getCode() ) {
            throw  new SellException( ResultEnum.UPDATE_ORDER_STATUS_FAIL );
        }
        orderMasterDTO.setOrderStatus( om.getOrderStatus() );
        if(orderMasterDTO.getOrderDetailList() == null ||  orderMasterDTO.getOrderDetailList().isEmpty()){
            throw  new RuntimeException("订单中没有子订单") ;
        }

        // 商品返还库存
        List<CartDTO> cartDTOList =
            orderMasterDTO.getOrderDetailList().stream()
                    .map( e -> (new CartDTO(e.getProductId(),e.getProductQuantity())))
                    .collect(Collectors.toList());

        productInfoService.increaseStock( cartDTOList );
        if( orderMasterDTO.getPayStatus().equals( PayStatusEnum.SUCCESS.getCode() )){
            //TODO 退款
        }
        return orderMasterDTO ;
    }

    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {

        // 判断订单状态, NEW 则可以 完结
        OrderMaster orderMaster = dao.findOne( orderMasterDTO.getOrderId() );
        if( orderMaster == null    )     throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        if( orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            log.error("修改订单状态操作不合规");
            throw new SellException( ResultEnum.ORDER_STATUS_WRONG );
        }


        orderMaster.setOrderStatus(OrderStatusEnum.FINISH .getCode());

        OrderMaster om = dao.save( orderMaster );
        orderMasterDTO.setOrderStatus( om.getOrderStatus() );

        return orderMasterDTO ;
    }

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {


        OrderMaster orderMaster = dao.findOne( orderMasterDTO.getOrderId() );
        if( orderMaster == null    )     throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        if( orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            log.error("修改订单状态操作不合规");
            throw new SellException( ResultEnum.ORDER_STATUS_WRONG );
        }

        if( orderMaster.getPayStatus() != PayStatusEnum.WAIT.getCode() ) {
            throw new SellException( ResultEnum.UPDATE_PAY_STATUS_FAIL );
        }
        // TODO 付款

        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());

        OrderMaster om = dao.save( orderMaster );
        orderMasterDTO.setOrderStatus( om.getPayStatus() );

        return orderMasterDTO ;
    }
}
