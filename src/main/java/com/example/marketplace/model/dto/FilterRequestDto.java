package com.example.marketplace.model.dto;

import com.example.marketplace.model.enums.FieldType;
import com.example.marketplace.model.enums.Operator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class FilterRequestDto implements Serializable {

    private String key;

    private Operator operator;

    private FieldType fieldType;
    private Object value;
    private Object valueTo;
    private List<Object> values;

}
