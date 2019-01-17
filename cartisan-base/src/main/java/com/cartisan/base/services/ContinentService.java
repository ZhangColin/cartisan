package com.cartisan.base.services;

import com.cartisan.base.dtos.ContinentDto;
import com.cartisan.base.repositories.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: ContinentService</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Service
public class ContinentService {
    @Autowired
    private ContinentRepository continentRepository;

    public List<ContinentDto> findContinents() {
        return continentRepository.findAll().stream().map(ContinentDto::convertFrom).collect(Collectors.toList());
    }
}
