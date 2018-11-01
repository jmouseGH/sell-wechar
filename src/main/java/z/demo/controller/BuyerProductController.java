package z.demo.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import z.demo.entity.ProductCategory;
import z.demo.entity.ProductInfo;
import z.demo.service.ProductCategoryService;
import z.demo.service.ProductInfoService;
import z.demo.vo.ProductInfoVo;
import z.demo.vo.ProductVo;
import z.demo.vo.ResultVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("//buyer/product")
public class BuyerProductController {

    @Autowired
    ProductInfoService productService;
    @Autowired
    ProductCategoryService categoryService;

    /**
     * 查询所有上架商品
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVo list() {
//        ResultVo<List<ProductVo>> rv = new ResultVo <>();
//
//
//        List<ProductInfo> productInfos = productService.findUp();
//
//        System.out.println("\n\n"+ productInfos);
//        Map<Integer, List<ProductInfoVo>> map = new HashMap<>();
//
//        for (ProductInfo productInfo : productInfos) {
//            // 取出 category type 作为k
//            // 然后放入对应的 v
//
//            System.out.println("\n\n"+ productInfo);
//
//            if (map.containsKey(productInfo.getCategoryType())) {
//                // 如果已经有了对应的key, 就把 productInfo放入对应的 V 的List中
//                map.get(productInfo.getCategoryType()).add(new ProductInfoVo(productInfo));
//            } else {
//                // 如果还没有对应的 category Type 在map 中, 那么新建一个键值对.
//                List<ProductInfoVo> list = new ArrayList<>( );
//                list.add( new ProductInfoVo( productInfo ));
//                map.put(productInfo.getCategoryType(),  list );
//            }
//
//
//        }
//
//
////        把map 转换成 productVo 放入到 rv 中
//        rv.setData( new ArrayList<ProductVo>() );
//
//        for (Map.Entry<Integer, List<ProductInfoVo>> entry : map.entrySet()) {
//
//            String categoryName = categoryService.findByCategoryType(entry.getKey()).getCategoryName();
//            Integer categoryType = entry.getKey();
//
//            rv.getData().add(new ProductVo(categoryName, categoryType, entry.getValue()));
//        }
//
//        rv.setCode(0);
//        rv.setMsg("成功");
//
//
//        return rv ;


        // 查询出所有上架的商品
        List<ProductInfo > productInfos = productService.findUp() ;
        // 查询出类目
        List<Integer>  categoryTypeList = productInfos.stream()
                .map( e->e.getCategoryType() )
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 数据 组装

        List<ProductVo> data = new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo() ;
            productVo .setCategoryName( productCategory.getCategoryName() );
            productVo .setCategoryType( productCategory.getCategoryType());
            List<ProductInfoVo> productInfoList = new ArrayList<>();

            for (ProductInfo productInfo : productInfos) {
                if( productInfo.getCategoryType().equals( productCategory.getCategoryType() )){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties( productInfo , productInfoVo ); // 将属性拷贝到 productInfoVo
                    productInfoList.add( productInfoVo );
                }
            }
            productVo.setProductVoList( productInfoList );
            data.add(productVo);
        }
        ResultVo<List<ProductVo>> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("success");
        resultVo.setData( data );


        return resultVo ;

    }
}
