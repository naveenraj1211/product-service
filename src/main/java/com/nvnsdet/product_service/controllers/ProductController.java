package com.nvnsdet.product_service.controllers;


import com.nvnsdet.product_service.dtos.ProductDto;
import com.nvnsdet.product_service.models.Product;
import com.nvnsdet.product_service.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("products")
@Validated  // 👈 enables method parameter validation
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(@Qualifier("sps") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable @Min(1) Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product.toProductDto());
    }

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = products.stream().map(Product::toProductDto)
                .toList();
        return ResponseEntity.ok(productDtos);
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productDto.toProduct();
        product = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product.toProductDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto>  updateProduct(@PathVariable @Min(1) Long id, @RequestBody ProductDto productDto) {
        Product product = productDto.toProduct();
        product = productService.replaceProduct(product, id);
        return ResponseEntity.ok(product.toProductDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable @Min(1) Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }


}
