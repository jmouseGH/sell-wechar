package z.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import z.demo.converter.OrderForm2OrderMasterDTO;
import z.demo.dto.OrderMasterDTO;
import z.demo.entity.OrderMaster;
import z.demo.enums.ResultEnum;
import z.demo.exception.SellException;
import z.demo.form.OrderForm;
import z.demo.service.BuyerService;
import z.demo.service.OrderMasterService;
import z.demo.util.ResultVoUtil;
import z.demo.vo.ProductInfoVo;
import z.demo.vo.ProductVo;
import z.demo.vo.ResultVo;

import javax.print.attribute.standard.PageRanges;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    OrderMasterService service ;
    @Autowired
    BuyerService buyerService ;

    // 创建订单

    /**前端传递参数
     * name: "张三"
     * phone: "18868822111"
     * address: "慕课网总部"
     * openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
     * items: [{
     *     productId: "1423113435324",
     *     productQuantity: 2 //购买数量
     * }]
     */
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create (@Valid OrderForm orderForm , BindingResult bindingResult) {
        if( bindingResult.hasErrors() ){
            log.error("创建订单失败, 参数不正确");
            throw new SellException( ResultEnum.PARAMER_ERROR ,"--------"+ bindingResult.getFieldError().getDefaultMessage());
        }

        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTO.convert(orderForm);
        if(CollectionUtils.isEmpty( orderMasterDTO.getOrderDetailList() )) throw new RuntimeException("购物车不能为空");

        OrderMasterDTO result = service.create( orderMasterDTO );
        System.out.println( result );
        Map<String, String > map = new HashMap<>();
        map.put("orderId", result.getOrderId() );

        return ResultVoUtil.succcess( map );


    }

    //订单详情

    @GetMapping("list")
    public ResultVo<List<ProductVo>> list (@RequestParam String openid ,
                                           @RequestParam(defaultValue ="0" ) Integer page,
                                           @RequestParam(defaultValue = "10") Integer size ) {
        if(StringUtils.isEmpty(openid )) throw new RuntimeException("openid不能为空");

        PageRequest pageRequest = new PageRequest(page, size );

        Page<OrderMasterDTO> p = service.findList( openid, pageRequest );

        return ResultVoUtil.succcess( p.getContent() );

    }
    // 订单列表
    @GetMapping("/detail")
    public ResultVo<OrderMasterDTO> detail (
            @RequestParam String  openid ,
            @RequestParam String  orderId
    ) {

        OrderMasterDTO orderMasterDTO = buyerService.findOrderOne( openid, orderId );

        return ResultVoUtil.succcess(orderMasterDTO );
    }
    // 取消订单

    @PostMapping("/cancel")
    public ResultVo cancel (
            @RequestParam String openid ,
            @RequestParam String  orderId
    ){
        buyerService.cancelOrderOne( openid,orderId );

        return ResultVoUtil.succcess(null);
    }
}
