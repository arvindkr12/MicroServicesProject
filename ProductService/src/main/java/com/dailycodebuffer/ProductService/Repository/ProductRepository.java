package com.dailycodebuffer.ProductService.Repository;

import com.dailycodebuffer.ProductService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
