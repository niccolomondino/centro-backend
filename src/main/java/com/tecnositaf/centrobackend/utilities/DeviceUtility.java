package com.tecnositaf.centrobackend.utilities;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;

public class DeviceUtility {
	
	private DeviceUtility() {}

	public static boolean isValidForInsert(DTODevice dtoDevice) {
        /*** decidiamo di rendere obbligatori solo 'type' e 'brand' ***/
        if( dtoDevice.getId()!=null ) return false;
        if( StringUtils.isEmpty(dtoDevice.getType()) )	return false;
        if( StringUtils.isEmpty(dtoDevice.getBrand()) )	return false;
        return true;
    }
	
	public static void checkAndSetTimestampsDevice(Device device) {

		if (device.getLastUpdate() == null)
			device.setLastUpdate(LocalDateTime.now());

		if (device.getRegistrationTime() == null)
			device.setRegistrationTime(LocalDateTime.now());
	}
}
