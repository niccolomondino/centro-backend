package com.tecnositaf.centrobackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tecnositaf.centrobackend.model.Device;

public interface DeviceService {

    List<Device> getDevices();
    
    void deleteDeviceById(String id);
    
    Optional<Device> getDeviceById(String id);
    
    Device getDeviceByIdNoOptional(String id);
    
    Device createDevice(Device device);
    
    //potrei unificarle?
    Device updateDevice(Device device);
	Device updateDeviceWithId(Device device, String id);
    
    ArrayList<Device> filterDeviceByTime(LocalDateTime dateTime);
    
    ArrayList<Device> filterDeviceByStorageYear(int nYear);

	boolean existsDeviceById(String id);

   
}
