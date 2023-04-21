package br.com.carv.grpc.mapper.impl;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;
import br.com.carv.grpc.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product toProduct(ProductCreateRequest productCreateRequest) {
        return new Product(productCreateRequest.getName(), productCreateRequest.getPrice(),
                productCreateRequest.getQuantityInStock());
    }

    @Override
    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                product.getQuantityInStock());
    }
}
