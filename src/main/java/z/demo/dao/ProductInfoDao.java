package z.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import z.demo.entity.ProductInfo;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String > {

    /**
     * 根据商品状态查询
     * 可查询出上架中的商品, 或下架的商品
     * @param productStatus
     * @return
     */
    List< ProductInfo > findByProductStatus ( Byte productStatus ) ;
}
