package com.nickz.flatfinder.mapper;

import com.nickz.flatfinder.config.MapstructConfig;
import com.nickz.flatfinder.entity.Apartment;
import com.nickz.flatfinder.feign.dto.ApartmentOnliner;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface ApartmentMapper {

    Apartment toEntity(ApartmentOnliner dto);
}
