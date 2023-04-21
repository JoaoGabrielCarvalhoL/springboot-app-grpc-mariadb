package br.com.carv.grpc.repository;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.exception.AlreadyExistsException;
import br.com.carv.grpc.util.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        Product product = ProductCreator.createProduct();
        Product productId = ProductCreator.createProductId();
        List<Product> productsList = new ArrayList<Product>();
        productsList.add(productId);

        BDDMockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(productId);

        BDDMockito.when(productRepository.findByNameIgnoreCase(Mockito.anyString()))
                .thenReturn(Optional.of(productId));

        BDDMockito.when(productRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(productId));

        BDDMockito.when(productRepository.findAll()).thenReturn(new ArrayList<Product>(productsList));

        BDDMockito.doNothing().when(productRepository).delete(Mockito.any(Product.class));

    }

    @Test
    public void createProductSuccess() {
        Product product = ProductCreator.createProduct();
        Product saved = productRepository.save(product);
        Assertions.assertThat(saved.getId()).isNotNull();
        Assertions.assertThat(saved.getId()).isEqualTo(1L);
        Assertions.assertThat(saved.getName()).isEqualTo(product.getName());
    }

    @Test
    public void findProductByIdSuccess() {
        Long id = 1L;
        Optional<Product> byId = productRepository.findById(id);
        Assertions.assertThat(byId).isPresent();
        Assertions.assertThat(byId.get().getId()).isEqualTo(id);
    }

    @Test
    public void findProductByName() {
        String param = "Product";
        Optional<Product> byNameIgnoreCase = productRepository.findByNameIgnoreCase(param);
        Assertions.assertThat(byNameIgnoreCase).isPresent();
    }

    @Test
    public void findAllProductsListSuccess() {
        List<Product> all = productRepository.findAll();
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all).isNotEmpty();
        Assertions.assertThat(all.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void deleteProductSuccess() {
        Product product = ProductCreator.createProductId();
        productRepository.delete(product);
        Assertions.assertThatNoException();
    }



}
