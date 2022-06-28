package com.jhan.JAmarket.domain.repository;

import com.jhan.JAmarket.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    public List<Product> getProductsFromPriceGreaterThan(Float price);
    Product save (Product product);
    void delete (int productId);
}
