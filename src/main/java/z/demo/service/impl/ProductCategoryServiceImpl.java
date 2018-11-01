package z.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import z.demo.dao.ProductCategoryDao;
import z.demo.entity.ProductCategory;
import z.demo.service.ProductCategoryService;

import java.util.List;

@Service        // 注解表示service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao dao ;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return dao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return dao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }

    @Override
    public ProductCategory findByCategoryType(Integer categoryType) {
        return dao.findByCategoryType( categoryType );
    }
}
