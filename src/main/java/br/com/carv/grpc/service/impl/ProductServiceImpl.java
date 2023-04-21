package br.com.carv.grpc.service.impl;

import br.com.carv.grpc.domain.Product;
import br.com.carv.grpc.domain.dto.request.ProductCreateRequest;
import br.com.carv.grpc.domain.dto.response.ProductResponse;
import br.com.carv.grpc.exception.AlreadyExistsException;
import br.com.carv.grpc.exception.NotFoundException;
import br.com.carv.grpc.mapper.ProductMapper;
import br.com.carv.grpc.repository.ProductRepository;
import br.com.carv.grpc.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = Logger.getLogger(ProductServiceImpl.class.getCanonicalName());
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest productCreateRequest) {
        checkDuplicity(productCreateRequest.getName());
        logger.info("Create Product! Saving product into database");
        Product product = productMapper.toProduct(productCreateRequest);
        productRepository.save(product);
        return productMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse findById(Long id) {
        logger.info("Find Product by id: " + id);
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting product into database with id: " + id);
        productRepository.delete(productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id)));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findByNameIgnoreCase(String name) {
        return productRepository.findByNameIgnoreCase(name);
    }

    private void checkDuplicity(String name) {
        this.productRepository.findByNameIgnoreCase(name).ifPresent( exception -> {
            throw new AlreadyExistsException(name);
        });
    }


}
