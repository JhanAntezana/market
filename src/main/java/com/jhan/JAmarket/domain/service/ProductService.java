package com.jhan.JAmarket.domain.service;

import com.jhan.JAmarket.domain.Product;
import com.jhan.JAmarket.domain.repository.ProductRepository;
import com.jhan.JAmarket.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }
    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }
    public List<Product> getProductsFromPriceGreaterThan(Float price){
        return productRepository.getProductsFromPriceGreaterThan(price);
    }

    public Product save (Product product){
        return productRepository.save(product);
    }
    public boolean delete (int productId){
        /*if(getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        } else {
            return false;
        }*/

        //Es equivalente al anterior, pero mas elegante
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);
    }

}
