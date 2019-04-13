package com.cartisan.management.modules.base.clients;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.responses.GenericResponse;
import com.cartisan.management.modules.base.dtos.CityDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
public class CityClient {
    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "cache:management:base:gateways:CityClient:findCities", key = "#countryId")
    public List<CityDto> findCities(Long countryId) {
        String params = "";
        if (countryId != null) {
            params = "countryId=" + countryId.toString();
        }

        return restTemplate.exchange(
                "http://base/city?" + params,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GenericResponse<List<CityDto>>>() {
                }).getBody().getData();

    }

    public PageResult<CityDto> searchCities(Long[] countryIds, String name, Integer currentPage, Integer pageSize) {
        String url = String.format("http://base/city/search/%s/%s", currentPage, pageSize);

        List<String> params = new ArrayList<>();

        if (countryIds != null) {
            params.add("countryIds=" + String.join(",",
                    Arrays.stream(countryIds).map(countryId -> countryId.toString()).collect(Collectors.toList())));
        }

        if (StringUtils.isNotBlank(name)) {
            params.add("name=" + name);
        }

        url += "?" + String.join("&", params);

        final PageResult<CityDto> body = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GenericResponse<PageResult<CityDto>>>() {
                }).getBody().getData();
        return body;
    }
}
