package com.hamedTech.eccomerce.product.mapper;

import com.hamedTech.eccomerce.category.Category;
import com.hamedTech.eccomerce.product.dto.ProductPurchaseResponse;
import com.hamedTech.eccomerce.product.dto.ProductRequest;
import com.hamedTech.eccomerce.product.dto.ProductResponse;
import com.hamedTech.eccomerce.product.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product productDtoToProduct(ProductRequest request) {

        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .id(request.id())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse productToProductResponse(Product product) {

        return new ProductResponse(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );

    }

    public ProductPurchaseResponse toProductPurchaseRespond(Product product, double quantity) {

        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
