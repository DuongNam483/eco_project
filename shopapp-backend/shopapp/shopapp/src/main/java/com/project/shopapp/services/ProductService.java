package com.project.shopapp.services;

import com.project.shopapp.dto.ProductDTO;
import com.project.shopapp.dto.ProductImageDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product createProduct(ProductDTO productDTO) throws DataNotFound;
    Product getProductById(Long id) throws Exception;
    Page<ProductResponse> getAllProducts(Pageable pageRequest);
    Product updateProduct(long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(long id);
    boolean existsByName(String name);
    ProductImage creatProductImages(Long productId, ProductImageDTO productImageDTO) throws DataNotFound;

}
