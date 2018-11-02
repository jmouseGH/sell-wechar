package z.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import z.demo.dto.CartDTO;
import z.demo.entity.ProductInfo;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne ( String  productId ) ;

    /**
     * 查询在架的商品
     * @return
     */
    List < ProductInfo > findUp () ;

    /**
     * 分页查询所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save ( ProductInfo productInfo );

    // 库存操作

    void increaseStock (List<CartDTO> cartDTOList) ;

    void decreaseStock ( List<CartDTO> cartDTOList ) ;
}
