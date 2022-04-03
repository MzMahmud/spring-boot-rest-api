package com.moazmahmud.spring_boot_rest_api.product;

import com.moazmahmud.spring_boot_rest_api.common.BaseRestController;
import com.moazmahmud.spring_boot_rest_api.common.RestResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController extends BaseRestController {

    private final ProductService productService;

    @GetMapping
    public RestResponse getProducts() {
        List<ProductResponse> products = productService.getProducts();
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(products)
                .build();
    }

    @GetMapping("/{id}")
    public RestResponse getProduct(
            @PathVariable("id") Long id
    ) {
        ProductResponse product = productService.getProduct(id);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(product)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(
            @RequestBody ProductAddRequest productAddRequest
    ) throws URISyntaxException {
        Product product = productService.addProduct(productAddRequest);
        String createdAt = "/api/v1/products/" + product.getId();
        return ResponseEntity
                .created(new URI(createdAt))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody ProductAddRequest productAddRequest
    ) {
        productService.updateProduct(id, productAddRequest);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateProduct(
            @PathVariable("id") Long id
    ) {
        productService.deleteProduct(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
