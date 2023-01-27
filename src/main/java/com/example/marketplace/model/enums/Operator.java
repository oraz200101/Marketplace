package com.example.marketplace.model.enums;

import com.example.marketplace.model.dto.FilterRequestDto;
import jakarta.persistence.criteria.*;


import java.time.LocalDateTime;


public enum Operator {
    EQUAL{
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDto requestDto, Predicate predicate){
            Object value=requestDto.getFieldType().parse(requestDto.getValue().toString());
            Expression<?> key=root.get(requestDto.getKey());
            return cb.and(cb.equal(key,value),predicate);
        }
    },
    NOT_EQUAL{
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDto requestDto, Predicate predicate){
            Object value=requestDto.getFieldType().parse(requestDto.getValue().toString());
            Expression<?> key=root.get(requestDto.getKey());
            return cb.and(cb.notEqual(key,value),predicate);
        }
    },
    LIKE {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDto requestDto, Predicate predicate) {
            Expression<String> key = root.get(requestDto.getKey());
            return cb.and(cb.like(cb.upper(key), "%" + requestDto.getValue().toString().toUpperCase() + "%"), predicate);
        }
    },
    BETWEEN {
        public <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDto requestDto, Predicate predicate) {
            Object value =requestDto.getFieldType().parse(requestDto.getValue().toString());
            Object valueTo = requestDto.getFieldType().parse(requestDto.getValueTo().toString());
            if (requestDto.getFieldType() == FieldType.DATE) {
                LocalDateTime startDate = (LocalDateTime) value;
                LocalDateTime endDate = (LocalDateTime) valueTo;
                Expression<LocalDateTime> key = root.get(requestDto.getKey());
                return cb.and(cb.and(cb.greaterThanOrEqualTo(key, startDate), cb.lessThanOrEqualTo(key, endDate)), predicate);
            }
            if (requestDto.getFieldType() != FieldType.CHAR && requestDto.getFieldType() != FieldType.BOOLEAN) {
                Number start = (Number) value;
                Number end = (Number) valueTo;
                Expression<Number> key = root.get(requestDto.getKey());
                return cb.and(cb.and(cb.ge(key, start), cb.le(key, end)), predicate);
            }

            return predicate;
        }
    };
    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder cb, FilterRequestDto requestDto, Predicate predicate);


}
