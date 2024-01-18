package com.example.library_online.mapper;

import com.example.library_online.dto.type.TypeResponse;
import com.example.library_online.entities.Type;

import java.util.List;

public interface TypeMapper {
    List<TypeResponse> toDtoS(List<Type> all);
}
