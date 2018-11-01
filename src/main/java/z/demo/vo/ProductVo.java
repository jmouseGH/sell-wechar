package z.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * {
 * "name": "好吃的",
 * "type": 2,
 * "foods": [
 * {
 * productInfo
 * }
 * ]
 * }
 */
@Data
public class ProductVo {

    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVo> productVoList;

    public ProductVo() {
    }

    public ProductVo(String categoryName, Integer categoryType, List<ProductInfoVo> productVoList) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.productVoList = productVoList;
    }


}
