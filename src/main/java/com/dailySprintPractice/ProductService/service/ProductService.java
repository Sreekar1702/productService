package com.dailySprintPractice.ProductService.service;

import com.dailySprintPractice.ProductService.model.ProductRequest;
import com.dailySprintPractice.ProductService.model.ProductResponse;

public interface ProductService {
    ProductResponse getProductById(long productId) ;

    long addProduct(ProductRequest productRequest);

    void reduceQuantity(long productId, long quantity);
}
