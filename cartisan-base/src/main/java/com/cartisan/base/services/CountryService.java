package com.cartisan.base.services;

import com.cartisan.base.domains.Country;
import com.cartisan.base.dtos.CountryDto;
import com.cartisan.base.repositories.CountryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: CountryService</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Cacheable(value="cache:base:services:CountryService:findCountries", key="#continentId")
    public List<CountryDto> findCountries(Long continentId) {
        return countryRepository.findByContinentId(continentId)
                .stream().map(CountryDto::convertFrom).collect(Collectors.toList());
    }

    public List<CountryDto> searchCountries(Long continentId, String name) {
        return countryRepository.findAll((Specification<Country>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (continentId != null) {
                predicateList.add(criteriaBuilder.equal(root.get("continentId"), continentId));
            }

            if (StringUtils.isNotBlank(name)) {
                predicateList.add(criteriaBuilder.like(root.get("name"),
                        "%" + name + "%"));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }).stream().map(CountryDto::convertFrom).collect(Collectors.toList());
    }
}
