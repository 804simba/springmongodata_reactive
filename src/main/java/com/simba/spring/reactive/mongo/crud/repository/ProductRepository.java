package com.simba.spring.reactive.mongo.crud.repository;

import com.simba.spring.reactive.mongo.crud.dto.ProductDTO;
import com.simba.spring.reactive.mongo.crud.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDTO> findByPriceBetween(Range<Double> priceRange);
}
