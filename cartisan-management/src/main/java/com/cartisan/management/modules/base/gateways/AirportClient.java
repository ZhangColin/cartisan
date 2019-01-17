package com.cartisan.management.modules.base.gateways;

import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.management.modules.base.dtos.AirportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: ContinentClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class AirportClient {
    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "cache:management:base:gateways:AirportClient:findAirport", key = "#cityId")
    public List<AirportDto> findAirport(Long cityId) {
        return restTemplate.exchange(
                "http://base/airport?cityId=" + cityId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AirportDto>>() {
                }).getBody();

    }

    public PageResult<AirportDto> searchAirports(Long[] cityIds, Integer currentPage, Integer pageSize) {
        String url = String.format("http://base/airport/search/%s/%s", currentPage, pageSize);
        if (cityIds != null) {
            url += "?" + String.join("&",
                    Arrays.stream(cityIds).map(cityId -> "cityIds=" + cityId).collect(Collectors.toList()));
        }

        final PageResult<AirportDto> body = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<PageResult<AirportDto>>>() {
                }).getBody().getData();
        return body;
    }
}
