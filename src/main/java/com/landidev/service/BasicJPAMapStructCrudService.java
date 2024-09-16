package com.landidev.service;

import com.landidev.exceptions.DataNotFoundException;
import com.landidev.model.converter.IMapStructureConverter;
import com.landidev.model.dto.PageItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BasicJPAMapStructCrudService<DTO, ENTITY,
        CONVERTER extends IMapStructureConverter,
        REPOSITORY extends JpaRepository<ENTITY, Integer>> implements IBasicCrudService<DTO> {

    protected final REPOSITORY repository;
    protected final CONVERTER converter;

    protected BasicJPAMapStructCrudService(REPOSITORY repository, CONVERTER converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public DTO get(Object id) {
        log.debug("M=get, id={}, message=Get a specific {}", id, getEntitySimpleName());
        var data = repository.findById(convertId(id));
        return (DTO) data.map(converter::convertToDto).orElse(null);
    }

    @Override
    public PageItems get(Pageable pageable) {
        log.debug("M=getAll, page={}, size={}, message=Getting a list of {}", pageable.getPageNumber(), pageable.getPageSize(), getEntitySimpleName());
        Page<ENTITY> all = repository.findAll(pageable);
        return createPage(all);
    }

    protected PageItems createPage(Page<ENTITY> page) {

        List<DTO> result = new ArrayList<>();
        page.forEach(entity -> result.add((DTO) converter.convertToDto(entity)));
        return new PageItems<DTO>()
                .content(result)
                .totalElements(Math.toIntExact(page.getTotalElements()))
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber());
    }

    @Override
    public DTO save(DTO data) {
        log.debug("M=save, data={}, message=Saving a {}", data, getEntitySimpleName());
        var saved = repository.save((ENTITY) converter.convertToEntity(data));
        return (DTO) converter.convertToDto(saved);
    }

    @Override
    public DTO update(Object id, DTO data) {
        log.debug("M=update, data={}, message=Updating a {}", data, getEntitySimpleName());
        var entity = repository.findById(convertId(id));
        var update = entity.orElseThrow(() -> new DataNotFoundException(getEntitySimpleName() + NOT_FOUND_TEXT));
        updateData(data, update);
        return (DTO) converter.convertToDto(repository.save(update));
    }

    private Integer convertId(Object id) {
        try {
            if (id != null) {
                if (id instanceof Integer value) {
                    return value;
                } else if (id instanceof String) {
                    return Integer.valueOf(String.valueOf(id));
                } else if (id instanceof BigInteger value) {
                    return value.intValue();
                }
            }
        } catch (Exception e) {
            log.error("id=" + id + ", message=Failed to convert id.", e);
        }
        //TODO put the message on the message structure
        throw new DataNotFoundException("Id don't exists");
    }

    @Override
    public DTO delete(Object id) {
        log.debug("M=delete, id={}, message=Delete a specific {}", id, getEntitySimpleName());
        var data = repository.findById(convertId(id));
        ENTITY entity = data.orElseThrow(() -> new DataNotFoundException(getEntitySimpleName() + NOT_FOUND_TEXT));
        if (isUpdateDeleteLogic()) {
            validateAlreadyDeleted(entity);
            updateDataForDelete(entity);
            repository.save(entity);
        } else {
            repository.delete(entity);
        }
        return (DTO) data.map(converter::convertToDto).orElse(null);
    }

    protected void validateAlreadyDeleted(ENTITY entity) {
    }

    protected void updateDataForDelete(ENTITY entity) {
    }

    protected boolean isUpdateDeleteLogic() {
        return false;
    }

    protected abstract String getEntitySimpleName();

    protected abstract void updateData(DTO dto, ENTITY entity);
}
