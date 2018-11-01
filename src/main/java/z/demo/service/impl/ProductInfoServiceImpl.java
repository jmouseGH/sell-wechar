package z.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import z.demo.dao.ProductInfoDao;
import z.demo.entity.ProductInfo;
import z.demo.enums.ProductStatusEnum;
import z.demo.service.ProductInfoService;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired ProductInfoDao dao ;

    @Override
    public ProductInfo findOne(String productId) {
        return dao.findOne( productId );
    }

    /**
     * 查询在架商品
     * @return
     */
    @Override
    public List<ProductInfo> findUp() {
        return dao.findByProductStatus( ProductStatusEnum.UP.getCode() );
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll( pageable );
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save( productInfo );
    }
}
