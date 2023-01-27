package com.example.marketplace.service.base;

import com.example.marketplace.model.dto.SearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<Class,CreateDto,UpdateDto>{
    Page<Class> getAll(Pageable pageable,int pageNumber);
    Page<Class> searchWithFilter(SearchRequestDto request, Pageable pageable,int pageNumber);
    Class create(CreateDto createDto);
    Class getById(Long id);
    Class update(UpdateDto updateDto);
    Class save(Class model);
    boolean existsById(Long id);
}
