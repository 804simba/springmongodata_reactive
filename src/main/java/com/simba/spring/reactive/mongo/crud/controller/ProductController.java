package com.simba.spring.reactive.mongo.crud.controller;

import com.simba.spring.reactive.mongo.crud.dto.ProductDTO;
import com.simba.spring.reactive.mongo.crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author TIMOTHY NGONADI
 * @link <a href="https://www.youtube.com/watch?v=bXcFCgQsvAE">...</a>
 * */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> products() {
        return  productService.getProducts();
    }

    @GetMapping(value = "/{productId}")
    public Mono<ProductDTO> getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @GetMapping(value = "/product-range")
    public Flux<ProductDTO> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return  productService.getProductInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDTO) {
        return  productService.saveProduct(productDTO);
    }

    @PutMapping(value = "/update/{productId}")
    public Mono<ProductDTO> updateProduct(@RequestBody Mono<ProductDTO> productDTO, @PathVariable String productId) {
        return  productService.updateProduct(productDTO, productId);
    }

    @DeleteMapping("/delete/{productId}")
    public Mono<Void> deleteProduct(@PathVariable String productId) {
        return productService.deleteProduct(productId);
    }
}
