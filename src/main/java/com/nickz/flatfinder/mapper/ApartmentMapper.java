package com.nickz.flatfinder.mapper;

import com.nickz.flatfinder.config.MapstructConfig;
import com.nickz.flatfinder.entity.Apartment;
import com.nickz.flatfinder.feign.dto.ApartmentKufar;
import com.nickz.flatfinder.feign.dto.ApartmentOnliner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface ApartmentMapper {

    @Mapping(target = "id", expression = "java( \"onliner_\" + dto.id())" )
    Apartment toEntity(ApartmentOnliner dto);

    @Mapping(target = "id", expression = "java( \"kufar_\" + dto.getId())" )
    Apartment toEntity(ApartmentKufar dto);
}
