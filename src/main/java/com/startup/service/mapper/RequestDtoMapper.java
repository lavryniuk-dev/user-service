package com.startup.service.mapper;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
