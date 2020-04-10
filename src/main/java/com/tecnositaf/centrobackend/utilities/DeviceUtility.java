package com.tecnositaf.centrobackend.utilities;

import org.springframework.util.StringUtils;

import com.tecnositaf.centrobackend.dto.DTODevice;

public class DeviceUtility {
	
	private DeviceUtility() {}

	public static boolean isValidForInsert(DTODevice dtoDevice) {
        /*** decidiamo di rendere obbligatori solo 'type' e 'brand' ***/
        if( dtoDevice.getId()!=null ) return false;
        if( StringUtils.isEmpty(dtoDevice.getType()) )	return false;
        if( StringUtils.isEmpty(dtoDevice.getBrand()) )	return false;
        return true;
    }
}
