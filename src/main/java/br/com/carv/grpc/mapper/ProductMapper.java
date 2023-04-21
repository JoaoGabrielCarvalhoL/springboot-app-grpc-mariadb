package br.com.carv.grpc.mapper;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;

public interface ProductMapper {

    Product toProduct(ProductCreateRequest productCreateRequest);

    ProductResponse toProductResponse(Product product);
}
