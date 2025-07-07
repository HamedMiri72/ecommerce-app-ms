package com.hamedTech.eccomerce.product.controller;


import com.hamedTech.eccomerce.product.dto.ProductPurchaseRequest;
import com.hamedTech.eccomerce.product.dto.ProductPurchaseResponse;
import com.hamedTech.eccomerce.product.dto.ProductRequest;
import com.hamedTech.eccomerce.product.dto.ProductResponse;
import com.hamedTech.eccomerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")

    public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequest request){

        return ResponseEntity.ok(productService.createProduct(request));
    }


    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody @Valid List<ProductPurchaseRequest> request){

        return ResponseEntity.ok(productService.purchaseProduct(request));

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer productId){

        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAllProducts());
    }



}
