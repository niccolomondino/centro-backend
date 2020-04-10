package com.tecnositaf.centrobackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tecnositaf.centrobackend.model.Device;

public interface DeviceService {

    List<Device> findAll();
    
    void deleteById(String id);
    
    Optional<Device> findById(String id);
    
    Device findByIdNoOptional(String id);
    
    Device save(Device device);
    
    
    
    ArrayList<Device> filterDeviceByTime(LocalDateTime dateTime);
    
    ArrayList<Device> filterDeviceByStorageYear(int nYear);

	boolean existsById(String id);

	void checkAndSetTimestampsDevice(Device device);

	//potrei unificarle
	Device update(Device device);
	Device updateWithId(Device device, String id);

	
    
   
}
