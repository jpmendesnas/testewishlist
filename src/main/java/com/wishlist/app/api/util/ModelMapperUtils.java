package com.wishlist.app.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

public final class ModelMapperUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);
    }

    private ModelMapperUtils() {}

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static <E, D> List<D> toDtoList(List<E> entityList, Class<D> dtoClass) {
        return entityList.stream().map(entity -> modelMapper.map(entity, dtoClass)).collect(Collectors.toList());
    }

    public static <D, E> List<E> toEntityList(List<D> dtoList, Class<E> entityClass) {
        return dtoList.stream().map(dto -> modelMapper.map(dto, entityClass)).collect(Collectors.toList());
    }
}
