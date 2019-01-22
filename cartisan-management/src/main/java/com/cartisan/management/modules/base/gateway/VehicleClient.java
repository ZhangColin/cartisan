package com.cartisan.management.modules.base.gateway;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.management.modules.base.dto.VehicleDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author colin
 */
@FeignClient(name = "base", fallback=VehicleClientFallback.class)
public interface VehicleClient {
    @Cacheable(value = "cache:management:base:gateways:VehicleClient:findVehicles",
            key = "#countryId==null?'0':#countryId")
    @GetMapping("/vehicle")
    GenericResponse<List<VehicleDto>> findVehicles(@RequestParam Long countryId);

    @PostMapping("vehicle")
    @CacheEvict(value="cache:management:base:gateways:VehicleClient:findVehicles", allEntries = true)
    void addVehicle(@RequestBody VehicleDto vehicleDto);

    @PutMapping("/vehicle/{id}")
    @CacheEvict(value="cache:management:base:gateways:VehicleClient:findVehicles", allEntries = true)
    void editVehicle(@PathVariable Long id, @RequestBody VehicleDto vehicleDto);

    @DeleteMapping("/vehicle/{id}")
    @CacheEvict(value="cache:management:base:gateways:VehicleClient:findVehicles", allEntries = true)
    void removeVehicle(@PathVariable Long id);
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
