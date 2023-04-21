package br.com.carv.grpc.util;

import br.com.carv.grpc.ProductRequest;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;

public class ProductRequestUtil {

    public static ProductCreateRequest toCreateRequest(ProductRequest productRequest) {
        return new ProductCreateRequest(productRequest.getName(),
                productRequest.getPrice(), productRequest.getQuantityInStock());
    }
}
