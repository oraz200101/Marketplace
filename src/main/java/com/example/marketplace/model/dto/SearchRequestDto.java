package com.example.marketplace.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SearchRequestDto implements Serializable {
    private List<FilterRequestDto>filterRequestDtos;
    private List<SortRequestDto>sortRequestDtos;

    public List<FilterRequestDto> getFilters() {
        if(Objects.isNull(this.filterRequestDtos)) return new ArrayList<>();
        return this.filterRequestDtos;
    }

    public List<SortRequestDto> getSorts() {
        if (Objects.isNull(this.sortRequestDtos)) return new ArrayList<>();
        return this.sortRequestDtos;
    }

}
