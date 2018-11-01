package z.demo.service;

import z.demo.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory findOne ( Integer categoryId) ;

    List<ProductCategory > findAll () ;

    List<ProductCategory> findByCategoryTypeIn ( List<Integer> categoryTypeList );

    ProductCategory save (ProductCategory productCategory) ;

    ProductCategory findByCategoryType ( Integer categoryType );
}
