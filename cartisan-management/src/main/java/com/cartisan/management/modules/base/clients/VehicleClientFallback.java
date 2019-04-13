package com.cartisan.management.modules.base.clients;

import com.cartisan.common.responses.GenericResponse;
import com.cartisan.management.modules.base.dtos.VehicleDto;
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
