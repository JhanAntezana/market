package com.jhan.JAmarket.persistence;

import com.jhan.JAmarket.persistence.crud.ProductoCrudRepository;
import com.jhan.JAmarket.persistence.entity.Compra;
import com.jhan.JAmarket.persistence.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }
    public Optional<List<Producto>> getEscasos(int cantidad){
        return  productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
    public List<Compra> getComprasUltimoMes(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return productoCrudRepository.findByFechaBetween(fechaInicio, fechaFin);
    }
    public List<Producto> getProductosCostosos(Float precioVenta){
        return productoCrudRepository.findByPrecioVentaGreaterThan(precioVenta);
    }
    public Optional<Producto> getProducto(int idProducto){
        return  productoCrudRepository.findById(idProducto);
    }
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
