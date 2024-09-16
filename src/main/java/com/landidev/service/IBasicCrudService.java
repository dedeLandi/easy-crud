package com.landidev.service;

import com.landidev.model.dto.PageItems;
import org.springframework.data.domain.Pageable;

public interface IBasicCrudService<VO> {

    String NOT_FOUND_TEXT = " not found";

    VO get(Object id);

    PageItems get(Pageable pageable);

    VO save(VO data);

    VO update(Object id, VO data);

    VO delete(Object id);

}
