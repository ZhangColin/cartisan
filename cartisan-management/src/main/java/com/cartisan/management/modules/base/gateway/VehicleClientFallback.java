package com.cartisan.management.modules.base.gateway;

import com.cartisan.common.response.GenericResponse;
import com.cartisan.management.modules.base.dto.VehicleDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author colin
 */
@Component
public class VehicleClientFallback implements VehicleClient {
    @Override
    public GenericResponse<List<VehicleDto>> findVehicles(Long countryId) {
        return null;
    }

    @Override
    public void addVehicle(VehicleDto vehicleDto) {

    }

    @Override
    public void editVehicle(Long id, VehicleDto vehicleDto) {

    }

    @Override
    public void removeVehicle(Long id) {

    }
}
