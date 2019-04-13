package com.cartisan.base.service;

import com.cartisan.base.domain.City;
import com.cartisan.base.dto.CityDto;
import com.cartisan.base.repository.CityRepository;
import com.cartisan.common.dto.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Cacheable(value="cache:base:services:CityService:findCities", key="#countryId")
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
                predicateList.add(root.<Long>get("countryId").in((Object[])countryIds));
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


