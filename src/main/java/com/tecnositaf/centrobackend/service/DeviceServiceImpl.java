package com.tecnositaf.centrobackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.annotation.PostConstruct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.Errors;
//import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.repository.DeviceRepository;
import com.tecnositaf.centrobackend.utilities.DateUtility;
import com.tecnositaf.centrobackend.utilities.DeviceUtility;

@Service
public class DeviceServiceImpl implements DeviceService{
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private DeviceRepository deviceRepository;
	
	
    @Override
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

	@Override
	public void deleteDeviceById(String id) {
		if(!deviceRepository.existsById(id)) 
			throw new ResourceNotFoundException("Device does not exist",Errors.RESULT_NOT_FOUND,HttpStatus.CONFLICT);
	
		deviceRepository.deleteById(id);
	}

	@Override
	public Optional<Device> getDeviceById(String id) {
		return deviceRepository.findById(id);
	}

	//method findById without using Optional<Device> 
	public Device getDeviceByIdNoOptional(String idDevice) {
		return getDeviceById(idDevice).isPresent() ? deviceRepository.findById(idDevice).get() : null;
	}

	@Override
	public Device createDevice(Device device) {
		DeviceUtility.checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public Device updateDevice(Device device) {
		if (!deviceRepository.existsById(device.getId())) 
			throw new ResourceNotFoundException("Device does not exist",Errors.RESULT_NOT_FOUND,HttpStatus.CONFLICT);
		
		DeviceUtility.checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public Device updateDeviceWithId(Device device, String id) {
		if (!deviceRepository.existsById(id)) 
			throw new ResourceNotFoundException("Device does not exist",Errors.RESULT_NOT_FOUND,HttpStatus.CONFLICT);
		
		DeviceUtility.checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public boolean existsDeviceById(String id) {
		return deviceRepository.existsById(id);
	}

	public ArrayList<Device> filterDeviceByTime(LocalDateTime dateTime) {
		ArrayList<Device> filteredListDevice = new ArrayList<>();
		ArrayList<Device> listDevices = (ArrayList<Device>) getDevices();

		//usare lamda e non for each classico
		listDevices.forEach(device ->{if (device.getRegistrationTime() != null) {
				if (dateTime.isBefore(device.getRegistrationTime()))
					filteredListDevice.add(device);
			}});
		
		return filteredListDevice;
	}
	

	public ArrayList<Device> filterDeviceByStorageYear(int storageYear) {
		
		ArrayList<Device> listDevices = (ArrayList<Device>) deviceRepository.findAll();
		ArrayList<Device> filteredListDevice = new ArrayList<>();
		
		listDevices.forEach(device ->{
			if(device.getRegistrationTime() != null && 
					DateUtility.calculateYearsFromCurrentTime(
							device.getRegistrationTime().getYear()) == storageYear
							) 
				filteredListDevice.add(device);
		});

		return filteredListDevice;
	}

}
