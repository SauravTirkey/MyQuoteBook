package com.scaler.productservicemorningbatch.services;

import com.scaler.productservicemorningbatch.dtos.FakeStoreProductDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import com.scaler.productservicemorningbatch.modles.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws InvalidProductIdException;
    List<Product> getAllProducts();
    Product updateProduct(Long id);
    Product replaceProduct(Long id, Product product);
    Product createProduct();
    void deleteProduct();
}
