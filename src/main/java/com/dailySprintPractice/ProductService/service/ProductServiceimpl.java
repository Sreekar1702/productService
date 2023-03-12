package com.dailySprintPractice.ProductService.service;

import com.dailySprintPractice.ProductService.entity.Product;
import com.dailySprintPractice.ProductService.exception.ProductServiceException;
import com.dailySprintPractice.ProductService.model.ProductRequest;
import com.dailySprintPractice.ProductService.model.ProductResponse;
import com.dailySprintPractice.ProductService.repository.ProductReposotory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceimpl implements ProductService{

    @Autowired
    private ProductReposotory productReposotory;

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product for id {}" + productId);
        Product product = productReposotory.findById(productId)
                .orElseThrow(()-> new ProductServiceException("product with id does not exist","Product_Not_Found"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("adding product");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productReposotory.save(product);
        return product.getProductId();
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing quantity");
        Product product = productReposotory.findById(productId)
                .orElseThrow(() -> new ProductServiceException("Product with given id not found","PRODUCT_NOT_FOUND"));
        if(product.getQuantity()<quantity){
            throw new ProductServiceException("Quantity is less than required","Quantity_not_available");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productReposotory.save(product);
        log.info("product updated successfully");

    }
}
