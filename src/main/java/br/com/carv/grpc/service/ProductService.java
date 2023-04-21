package br.com.carv.grpc.service;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductResponse create(ProductCreateRequest productCreateRequest);

    ProductResponse findById(Long id);

    void delete(Long id);

    List<ProductResponse> findAll();

    Optional<Product> findByNameIgnoreCase(String name);
}
