package com.cartisan.management.modules.base.gateways;

import com.cartisan.common.entity.Result;
import com.cartisan.management.modules.base.dtos.VehicleDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Title: ContinentClient</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@FeignClient(name = "cartisan-base")
public interface VehicleClient {
    @Cacheable(value = "cache:management:base:gateways:VehicleClient:findVehicles",
            key = "#countryId==null?'0':#countryId")
    @GetMapping("/base/vehicle")
    Result<List<VehicleDto>> findVehicles(@RequestParam Long countryId);
}
//@Component
//public class VehicleClient {
//
//    public List<VehicleDto> findVehicles(Long countryId) {
//        RestTemplate restTemplate = new RestTemplate();
//        String params = "";
//        if (countryId != null) {
//            params = "countryId="+countryId.toString();
//        }
//
//        return restTemplate.exchange(
//                "http://cartisan-base/base/vehicle?"+params,
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<Result<List<VehicleDto>>>() {}).getBody().getData();
//
//    }
//}
