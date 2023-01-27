package com.example.marketplace.specification;

import com.example.marketplace.model.dto.FilterRequestDto;
import com.example.marketplace.model.dto.SearchRequestDto;
import com.example.marketplace.model.dto.SortRequestDto;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SearchSpecification <T> implements Specification<T> {

    private  final SearchRequestDto requestDto;

    public SearchSpecification(SearchRequestDto requestDto) {
        this.requestDto = requestDto;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {


        Predicate predicate=cb.equal(cb.literal(Boolean.TRUE),Boolean.TRUE);
        for (FilterRequestDto filterRequestDto:this.requestDto.getFilters()){
            log.info("Filter: {} {} {}",filterRequestDto.getKey(),filterRequestDto.getOperator().toString(),
                    filterRequestDto.getValue());
            predicate=filterRequestDto.getOperator().build(root,cb,filterRequestDto,predicate);
        }

        List<Order> orders=new ArrayList<>();
        for (SortRequestDto sortRequestDto:this.requestDto.getSorts()){
            orders.add(sortRequestDto.getDirection().build(root,cb,sortRequestDto));
        }
        query.orderBy(orders);

        return predicate;
    }
}
