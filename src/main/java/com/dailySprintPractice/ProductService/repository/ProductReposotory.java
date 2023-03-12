package com.dailySprintPractice.ProductService.repository;

import com.dailySprintPractice.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposotory extends JpaRepository<Product,Long> {

}
