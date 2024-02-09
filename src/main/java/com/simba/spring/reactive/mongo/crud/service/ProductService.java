package com.simba.spring.reactive.mongo.crud.service;

import com.simba.spring.reactive.mongo.crud.dto.ProductDTO;
import com.simba.spring.reactive.mongo.crud.repository.ProductRepository;
import com.simba.spring.reactive.mongo.crud.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDTO> getProducts() {
        return productRepository.findAll().map(MapperUtils::entityToDto);
    }

    public Mono<ProductDTO> getProduct(String productId) {
        return productRepository.findById(productId).map(MapperUtils::entityToDto);
    }

    public Flux<ProductDTO> getProductInRange(double min, double max) {
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDTO> saveProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono.map(MapperUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(MapperUtils::entityToDto);
    }

    public Mono<ProductDTO> updateProduct(Mono<ProductDTO> productDTOMono, String productId) {
        return productRepository.findById(productId)
                .flatMap(product -> productDTOMono.map(MapperUtils::dtoToEntity)
                .doOnNext(p -> p.setId(productId)))
                .flatMap(productRepository::save)
                .map(MapperUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(String productId) {
        return productRepository.deleteById(productId);
    }
}
