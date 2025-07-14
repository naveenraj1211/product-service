package com.nvnsdet.product_service.repos;

import com.nvnsdet.product_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    long countByCategoryId(Long categoryId);

    //List<Product>  findProductOrderByPriceDesc();
    List<Product> findProductByOrderByPriceDesc();

    @Query("SELECT p.name FROM Product p WHERE p.id = ?1")
    String findProductNameById(Long productId);

    @Query("SELECT c.name FROM Category c join Product p on c.id = p.category.id WHERE p.id = ?1")
    String findCategoryNameByProductId(Long productId);
}
