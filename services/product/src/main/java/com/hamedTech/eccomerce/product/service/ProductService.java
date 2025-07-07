package com.hamedTech.eccomerce.product.service;

import com.hamedTech.eccomerce.product.dto.ProductPurchaseRequest;
import com.hamedTech.eccomerce.product.dto.ProductPurchaseResponse;
import com.hamedTech.eccomerce.product.dto.ProductRequest;
import com.hamedTech.eccomerce.product.dto.ProductResponse;
import com.hamedTech.eccomerce.product.entity.Product;
import com.hamedTech.eccomerce.product.exception.ProductPurchaseException;
import com.hamedTech.eccomerce.product.mapper.ProductMapper;
import com.hamedTech.eccomerce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public String createProduct(@Valid ProductRequest request) {

        var product = productMapper.productDtoToProduct(request);
        productRepository.save(product);
        return product.getId().toString();
    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {

        var productIds = request.
                stream()
                .map(ProductPurchaseRequest::productId)
                .collect(Collectors.toList());
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One Or more product dosenot exist");
        }
        var sortedRequest = request.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .collect(Collectors.toList());

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0; i< storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException(toString().formatted("Insufficient stock quantity for product with ID:: %s", productRequest.productId()));
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseRespond(product, productRequest.quantity()));
        }

        return purchasedProducts;

    }

    public ProductResponse findById(Integer productId) {

       var product = productRepository.findById(productId)
               .orElseThrow(() -> new EntityNotFoundException(format("Product not found by provided ID:: %s", productId)));

       ProductResponse response = productMapper.productToProductResponse(product);

       return response;
    }


    public List<ProductResponse> findAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = products.stream()
                .map(product -> productMapper.productToProductResponse(product))
                .collect(Collectors.toList());

        return productResponses;
    }
}
