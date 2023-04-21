package br.com.carv.grpc.resource;

import br.com.carv.grpc.*;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductResourceIntegrationTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub serviceBlockingStub;

    @Test
    @Order(1)
    public void createProductSuccessTest() {
        ProductRequest productTestIntegration = ProductRequest.newBuilder()
                .setName("Product Test Integration")
                .setPrice(10.00d)
                .setQuantityInStock(10)
                .build();

        ProductResponse response = serviceBlockingStub.create(productTestIntegration);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(productTestIntegration).usingRecursiveComparison()
                .comparingOnlyFields("name", "price", "quantityInStock")
                .isEqualTo(response);
    }

    @Test
    @Order(2)
    public void createProductExceptionAlreadyExistsTest() {
        ProductRequest productTestIntegration = ProductRequest.newBuilder()
                .setName("Product Test Integration")
                .setPrice(10.00d)
                .setQuantityInStock(10)
                .build();

        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.create(productTestIntegration));
    }

    @Test
    @Order(3)
    public void findByIdSuccessTest() {
        RequestById request = RequestById.newBuilder().setId(1L).build();
//        setupProduct();
        ProductResponse response = serviceBlockingStub.findById(request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();
    }

    @Test
    @Order(4)
    public void findByIdNotFoundExceptionTest() {
        RequestById request = RequestById.newBuilder().setId(10L).build();

        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.findById(request));
    }

    @Test
    @Order(6)
    public void deleteSuccessTest() {
//        setupProduct();
        RequestById request = RequestById.newBuilder().setId(1L).build();
        Assertions.assertThatNoException().isThrownBy(() -> serviceBlockingStub.delete(request));
    }

    @Test
    @Order(7)
    public void deleteNotFoundExceptionTest() {
        RequestById request = RequestById.newBuilder().setId(1L).build();

        Assertions.assertThatExceptionOfType(StatusRuntimeException.class)
                .isThrownBy(() -> serviceBlockingStub.delete(request));
    }

    @Test
    @Order(5)
    public void findAllSuccessTest() {
        ProductResponseList all = this.serviceBlockingStub.findAll(EmptyRequest.newBuilder().build());
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.getProductsCount()).isEqualTo(1);
    }

    private void setupProduct() {
        ProductRequest productTestIntegration = ProductRequest.newBuilder()
                .setName("Product Test Integration")
                .setPrice(10.00d)
                .setQuantityInStock(10)
                .build();

        ProductResponse response = serviceBlockingStub.create(productTestIntegration);
    }
}
