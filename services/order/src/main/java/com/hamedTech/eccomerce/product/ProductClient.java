package com.hamedTech.eccomerce.product;

import com.hamedTech.eccomerce.dto.ProductPurchaseResponse;
import com.hamedTech.eccomerce.dto.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service", url = "${applocation.product-service.url}")
public interface ProductClient {

    @PostMapping("/purchase")
    Optional<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<PurchaseRequest> request);
}
