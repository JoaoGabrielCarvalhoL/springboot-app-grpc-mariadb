package br.com.carv.grpc.util;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;

public class ProductCreator {

    public static ProductCreateRequest createProductRequest() {
        return new ProductCreateRequest("Product Test Insert Request",
                10.00d, 10);
    }

    public static ProductResponse createProductResponse() {
        return new ProductResponse(1L, "Product Test Insert Request",
                10.00d, 10);
    }

    public static Product createProduct () {
        return new Product("Product Test Insert Request",
                10.00d, 10);
    }

    public static Product createProductId() {
        return new Product(1L, "Product Test Insert Request",
                10.00d, 10);
    }
}
