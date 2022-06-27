package com.jhan.JAmarket.persistence;

import com.jhan.JAmarket.domain.Product;
import com.jhan.JAmarket.domain.repository.ProductRepository;
import com.jhan.JAmarket.persistence.crud.ProductoCrudRepository;
import com.jhan.JAmarket.persistence.entity.Compra;
import com.jhan.JAmarket.persistence.entity.Producto;
import com.jhan.JAmarket.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper productMapper;
    @Autowired
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(pro -> productMapper.toProducts(pro));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(pro -> productMapper.toProduct(pro));
    }

    @Override
    public Product save(Product product) {
        return productMapper.toProduct(productoCrudRepository.save(productMapper.toProducto(product)));
    }

    public List<Compra> getComprasUltimoMes(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return productoCrudRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    public List<Producto> getProductosCostosos(Float precioVenta){
        return productoCrudRepository.findByPrecioVentaGreaterThan(precioVenta);
    }
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
