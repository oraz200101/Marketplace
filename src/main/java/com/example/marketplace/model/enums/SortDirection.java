package com.example.marketplace.model.enums;

import com.example.marketplace.model.dto.SortRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

public enum SortDirection {
    ASC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDto requestDto) {
            return cb.asc(root.get(requestDto.getKey()));
        }
    },
    DESC {
        public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDto requestDto) {
            return cb.desc(root.get(requestDto.getKey()));
        }
    };

    public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequestDto requestDto);
}
