package com.example.demo.ordering.mappers;

import com.example.demo.ordering.dtos.ProductDto;
import com.example.demo.ordering.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "productTypeName", target = "productType.name")
    @Mapping(source = "productTypeId", target = "productType.id")
    Product toEntity(ProductDto productDto1);

    List<ProductDto> toDtoList(List<Product> products);

    @InheritInverseConfiguration(name = "toEntity")
    ProductDto toDto(Product product);

}