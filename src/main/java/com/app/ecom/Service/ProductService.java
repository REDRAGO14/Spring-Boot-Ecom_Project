package com.app.ecom.Service;

import com.app.ecom.DTO.ProductRequest;
import com.app.ecom.DTO.ProductResponse;
import com.app.ecom.Model.Product;
import com.app.ecom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public ProductResponse create(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);

    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());
        product.setCatagory(productRequest.getCatagory());
    }

    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setActive(savedProduct.isActive());
        response.setCatagory(savedProduct.getCatagory());
        return response;
    }

    public Optional<ProductResponse> update(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingProduct->{
                    updateProductFromRequest(existingProduct, productRequest);
                    Product savedProduct = productRepository.save(existingProduct);
                    return mapToProductResponse(savedProduct);
                });
    }

    public List<ProductResponse> fetchProducts() {
        return productRepository.findByActiveTrue().stream()
                .map(product ->  mapToProductResponse(product))
                .collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
         return productRepository.findById(id)
                 .map(product ->{
                         product.setActive(false);
                         productRepository.save(product);
                         return true;
                 }).orElse( false);
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProduct(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
}
