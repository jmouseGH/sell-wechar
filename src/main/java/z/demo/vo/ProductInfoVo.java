package z.demo.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import z.demo.entity.ProductInfo;

import java.math.BigDecimal;

/**
 * {
 * "id": "123457",
 * "name": "慕斯蛋糕",
 * "price": 10.9,
 * "description": "美味爽口",
 * "icon": "http://xxx.com",
 * }
 */
@Data
public class ProductInfoVo {

    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescript;
    @JsonProperty("icon")
    private String productIcon;

    public ProductInfoVo() {

    }

    public ProductInfoVo(String productId, String productName, BigDecimal productPrice, String productDescript, String productIcon) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescript = productDescript;
        this.productIcon = productIcon;
    }

    public ProductInfoVo(ProductInfo productInfo) {
        this.productId = productInfo.getProductId();
        this.productName = productInfo.getProductName();
        this.productPrice = productInfo.getProductPrice();
        this.productDescript = productInfo.getProductDescription();
        this.productIcon = productInfo.getProductIcon();
    }
}
