package com.jhan.JAmarket.persistence.mapper;

import com.jhan.JAmarket.domain.Category;
import com.jhan.JAmarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")//MapStruct nos ofrece una integración con spring
public interface CategoryMapper {
    @Mappings({//convertiendo Categoria a Category
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory (Categoria categoria);//para convertir Categoria en Category

    @InheritInverseConfiguration//indicando que esta conversión es la inversa al Mappings que ya tenemos (el de arriba)
    @Mapping(target = "productos", ignore = true)//para indicar que ignore la conversión al Lista de productos (no va mapear)
    Categoria toCategoria (Category category);
}
