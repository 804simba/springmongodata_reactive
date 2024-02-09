package com.simba.spring.reactive.mongo.crud.utils;

import com.simba.spring.reactive.mongo.crud.dto.ProductDTO;
import com.simba.spring.reactive.mongo.crud.entity.Product;
import org.springframework.beans.BeanUtils;

public class MapperUtils {

    public static ProductDTO entityToDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static Product dtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
