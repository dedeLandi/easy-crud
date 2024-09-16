package com.landidev.model.converter;

public interface IMapStructureConverter<DTO, ENTITY> {

    DTO convertToDto(ENTITY entity);

    ENTITY convertToEntity(DTO entity);

}
