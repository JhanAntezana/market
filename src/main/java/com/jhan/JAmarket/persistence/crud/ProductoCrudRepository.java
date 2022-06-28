package com.jhan.JAmarket.persistence.crud;

import com.jhan.JAmarket.persistence.entity.Compra;
import com.jhan.JAmarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {//recibe la clase  y el tipo de dato de la clave primaria
    //recuperar toda la lista de productos que perteneces a una caegoria en específica
    /*con Query nativo
    @Query(value = "select * from productos where id_categoria = ?", nativeQuery = true)
     */
    //con Query methods
    List<Producto> findByIdCategoriaOrderByNombreAsc (int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
    List<Producto> findByPrecioVentaGreaterThan(Float precioVenta);
}
