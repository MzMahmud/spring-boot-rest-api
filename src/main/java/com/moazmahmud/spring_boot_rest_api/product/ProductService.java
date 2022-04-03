package com.moazmahmud.spring_boot_rest_api.product;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return productRepository.findById(id);
    }

    private ProductResponse mapEntityToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        return response;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    public ProductResponse getProduct(Long id) {
        return findById(id)
                .map(this::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No Product found with id=" + id));
    }

    private Product mapAddRequestToEntity(ProductAddRequest addRequest,
                                          Long id,
                                          Product product) {
        product.setId(id);
        product.setName(addRequest.getName());
        product.setDescription(addRequest.getDescription());
        product.setPrice(addRequest.getPrice());
        return product;
    }

    @Transactional
    public Product addProduct(ProductAddRequest productAddRequest) {
        Product product = mapAddRequestToEntity(productAddRequest, null, new Product());
        return productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Long id, ProductAddRequest productAddRequest) {
        Product product = findById(id).orElseThrow(() -> new NotFoundException("No Product found with id=" + id));
        mapAddRequestToEntity(productAddRequest, id, product);
    }

    public void deleteProduct(Long id) {
        Product product = findById(id).orElseThrow(() -> new NotFoundException("No Product found with id=" + id));
        productRepository.delete(product);
    }
}
