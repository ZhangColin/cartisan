package com.cartisan.management.modules.base.gateways;

import com.cartisan.common.entity.PageResult;
import com.cartisan.common.entity.Result;
import com.cartisan.management.modules.base.dtos.AirportDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    public PageResult<List<AirportDto>> findAirport(Long cityId, Integer currentPage, Integer pageSize) {
        String params = "pageSize=" + pageSize + "&currentPage=" + currentPage;
        if (cityId != null) {
            params += "&cityId=" + cityId.toString();
        }

        final PageResult<List<AirportDto>> body = restTemplate.exchange(
                "http://cartisan-base/base/airport?" + params,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PageResult<List<AirportDto>>>() {
                }).getBody();
        return body;

    }

    public PageResult<AirportDto> searchAirports(Long[] cityIds, Integer currentPage, Integer pageSize) {
        String url = String.format("http://cartisan-base/base/airport/search/%s/%s", currentPage, pageSize);
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
