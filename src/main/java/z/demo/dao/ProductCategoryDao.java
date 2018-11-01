package z.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import z.demo.entity.ProductCategory;

import java.util.List;

/**
 * productcategorydao ,
 * 用于对数据库中的商品类别表 进行操作
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer > {


    List<ProductCategory> findByCategoryTypeIn ( List<Integer> categoryTypeList );

    ProductCategory findByCategoryType ( Integer categoryType );
}
