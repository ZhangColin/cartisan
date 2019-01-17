package com.cartisan.base.services;

import com.cartisan.base.domains.City;
import com.cartisan.base.dtos.CityDto;
import com.cartisan.base.repositories.CityRepository;
import com.cartisan.common.entity.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: CityService</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityDto> findCities(Long countryId) {
        return cityRepository.findByCountryId(countryId).stream()
                .map(CityDto::convertFrom).collect(Collectors.toList());
    }

    public PageResult<CityDto> searchCities(
            Long[] countryIds, String name, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<City> searchResult = cityRepository.findAll((Specification<City>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (countryIds != null) {
                predicateList.add(root.<Long>get("countryId").in(countryIds));
            }

            if (StringUtils.isNotBlank(name)) {
                predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.getContent().stream().map(CityDto::convertFrom).collect(Collectors.toList()));
    }
}


