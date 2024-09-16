package com.landidev.model.converter;

import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface IBasicConverter<ENTITY, VO, PAGEVO> {

    default List<VO> toVO(List<ENTITY> all) {
        return Optional.ofNullable(all)
                .map(p -> p.stream()
                        .map(this::fromEntityToVO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    PAGEVO toVO(Page<ENTITY> page, String locale, Function<ENTITY, VO> function);

    VO fromEntityToVO(ENTITY entity);

    ENTITY toEntity(VO data);
}
