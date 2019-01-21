package com.cartisan.base.service;

import com.cartisan.base.dto.ContinentDto;
import com.cartisan.base.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class ContinentService {
    @Autowired
    private ContinentRepository continentRepository;

    @Cacheable(value="cache:base:services:ContinentService:findContinents")
    public List<ContinentDto> findContinents() {
        return continentRepository.findAll().stream().map(ContinentDto::convertFrom).collect(Collectors.toList());
    }
}
