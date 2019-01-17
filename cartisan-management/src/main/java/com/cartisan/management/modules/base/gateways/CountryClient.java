package com.cartisan.management.modules.base.gateways;

import com.cartisan.common.entity.Result;
import com.cartisan.management.modules.base.dtos.CountryDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ContinentClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class CountryClient {
    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "cache:management:base:gateways:CountryClient:findCountries", key = "#continentId")
    public List<CountryDto> findCountries(Long continentId) {

        return restTemplate.exchange(
                "http://base/country?continentId=" + continentId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<List<CountryDto>>>() {
                }).getBody().getData();

    }

    public List<CountryDto> searchCountries(Long continentId, String name) {
        List<String> params = new ArrayList<>();
        if (continentId != null) {
            params.add("continentId=" + continentId);
        }
        if (StringUtils.isNotBlank(name)) {
            params.add("name=" + name);
        }

        return restTemplate.exchange(
                "http://base/country/search?" + String.join("&", params),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<List<CountryDto>>>() {
                }).getBody().getData();

    }
}
