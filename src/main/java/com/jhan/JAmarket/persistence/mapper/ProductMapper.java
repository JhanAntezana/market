package com.jhan.JAmarket.persistence.mapper;

import com.jhan.JAmarket.domain.Product;
import com.jhan.JAmarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})//conmo Category tiene su Mapper propio, entonces se incluye un nuevo parametro
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Object producto);
    //no se implementa el @Mappings, ya que internamente MapStruct entiende que esta se comportara igual que la anterior ya que es el mismo tipo de conversión
    List<Product> toProducts(List<Producto> productos);
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
