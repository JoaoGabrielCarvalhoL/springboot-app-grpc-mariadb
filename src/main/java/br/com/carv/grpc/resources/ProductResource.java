package br.com.carv.grpc.resources;

import br.com.carv.grpc.*;
import br.com.carv.grpc.service.ProductService;
import br.com.carv.grpc.util.ProductRequestUtil;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class ProductResource extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        br.com.carv.grpc.domain.dto.response.ProductResponse productResponse =
                this.productService.create(ProductRequestUtil.toCreateRequest(request));

        ProductResponse response = ProductResponse.newBuilder()
                .setId(productResponse.getId())
                .setName(productResponse.getName())
                .setPrice(productResponse.getPrice())
                .setQuantityInStock(productResponse.getQuantityInStock())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(RequestById request, StreamObserver<ProductResponse> responseObserver) {
        br.com.carv.grpc.domain.dto.response.ProductResponse productResponse =
                productService.findById(request.getId());

        ProductResponse response = ProductResponse.newBuilder()
                .setId(productResponse.getId())
                .setName(productResponse.getName())
                .setPrice(productResponse.getPrice())
                .setQuantityInStock(productResponse.getQuantityInStock())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestById request, StreamObserver<EmptyResponse> responseObserver) {
        this.productService.delete(request.getId());
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(EmptyRequest request, StreamObserver<ProductResponseList> responseObserver) {
        List<br.com.carv.grpc.domain.dto.response.ProductResponse> list = this.productService.findAll();
        List<ProductResponse> collect = list.stream().map(item ->
                ProductResponse.newBuilder()
                        .setId(item.getId())
                        .setName(item.getName())
                        .setPrice(item.getPrice())
                        .setQuantityInStock(item.getQuantityInStock())
                        .build()
        ).collect(Collectors.toList());

        ProductResponseList response = ProductResponseList.newBuilder().addAllProducts(collect).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
