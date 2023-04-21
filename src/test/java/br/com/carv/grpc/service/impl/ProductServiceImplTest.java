package br.com.carv.grpc.service.impl;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;
import br.com.carv.grpc.exception.AlreadyExistsException;
import br.com.carv.grpc.exception.NotFoundException;
import br.com.carv.grpc.mapper.impl.ProductMapperImpl;
import br.com.carv.grpc.repository.ProductRepository;
import br.com.carv.grpc.service.ProductService;
import br.com.carv.grpc.util.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Test
    @Order(1)
    void create() {
        ProductCreateRequest productCreateRequest =
                new ProductCreateRequest("Product Test", 10.00d, 10);

        ProductResponse productResponse = productService.create(productCreateRequest);
        Assertions.assertThat(productResponse.getId()).isNotNull();
        Assertions.assertThat(productResponse.getName()).isEqualTo(productCreateRequest.getName());
        Assertions.assertThat(productResponse.getPrice()).isEqualTo(productCreateRequest.getPrice());
        Assertions.assertThat(productResponse.getQuantityInStock()).isEqualTo(productCreateRequest.getQuantityInStock());

        System.out.println(productResponse);
    }

    @Test
    public void createProductExceptionAlreadyExists() {
        ProductCreateRequest productCreateRequest = ProductCreator.createProductRequest();
        productService.create(productCreateRequest);
        Assertions.assertThatExceptionOfType(AlreadyExistsException.class)
                .isThrownBy(() -> productService.create(productCreateRequest));
    }

    @Test
    @Order(2)
    void findById() {
        ProductResponse product = productService.findById(1L);
        Assertions.assertThat(product.getId()).isNotNull();
        Assertions.assertThat(product.getId()).isEqualTo(1L);
        Assertions.assertThat(product.getName()).isEqualTo("Product Test");
    }

    @Test
    public void findByIdExceptionNotFound() {
        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> productService.findById(1L));
    }

    @Test
    @Order(3)
    void findAll() {
        List<ProductResponse> list = productService.findAll();
        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list.get(0).getId()).isNotNull();
        Assertions.assertThat(list.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(list.get(0).getName()).isEqualTo("Product Test");
    }

    @Test
    @Order(4)
    void findByNameIgnoreCase() {
        String param = "Product Test";
        Optional<Product> product = productService.findByNameIgnoreCase(param);
        Assertions.assertThat(product).isPresent();
        Assertions.assertThat(product.get().getName()).isEqualTo(param);
    }


    @Test
    @Order(5)
    void delete() {
        Assertions.assertThatNoException().isThrownBy(() -> productService.delete(1L));
    }
}