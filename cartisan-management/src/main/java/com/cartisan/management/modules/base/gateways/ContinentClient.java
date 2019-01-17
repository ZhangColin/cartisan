package com.cartisan.management.modules.base.gateways;

import com.cartisan.common.entity.Result;
import com.cartisan.management.modules.base.dtos.ContinentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p>Title: ContinentClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Component
public class ContinentClient {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Cacheable(value = "cache:management:base:gateways:ContinentClient:findContinents")
    public List<ContinentDto> findContinents() {
        final ServiceInstance serviceInstance = loadBalancerClient.choose("base");
        final String url = String.format("http://%s:%s/base/continent",
                serviceInstance.getHost(), serviceInstance.getPort());

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Result<List<ContinentDto>>>() {}).getBody().getData();
    }
}
