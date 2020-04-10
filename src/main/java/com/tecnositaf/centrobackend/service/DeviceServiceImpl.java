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
import org.springframework.stereotype.Service;

//import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.repository.DeviceRepository;
import com.tecnositaf.centrobackend.utilities.DateUtility;

@Service
public class DeviceServiceImpl implements DeviceService{
	
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private DeviceRepository deviceRepository;
	
	
    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

	@Override
	public void deleteById(String id) {
		if(!deviceRepository.existsById(id)) 
			throw new ResourceNotFoundException(60,"ResourceNotFoundException");
	
		deviceRepository.deleteById(id);
	}

	@Override
	public Optional<Device> findById(String id) {
		return deviceRepository.findById(id);
	}

	//method findById without using Optional<Device> 
	public Device findByIdNoOptional(String idDevice) {
		return findById(idDevice).isPresent() ? deviceRepository.findById(idDevice).get() : null;
	}

	@Override
	public Device save(Device device) {
		checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public Device update(Device device) {
		if (!deviceRepository.existsById(device.getId())) 
			throw new ResourceNotFoundException(60,"ResourceNotFoundException");
		
		checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public Device updateWithId(Device device, String id) {
		if (!deviceRepository.existsById(id)) 
			throw new ResourceNotFoundException(60,"ResourceNotFoundException");
		
		checkAndSetTimestampsDevice(device);
		return deviceRepository.save(device);
	}
	
	@Override
	public boolean existsById(String id) {
		return deviceRepository.existsById(id);
	}

	public ArrayList<Device> filterDeviceByTime(LocalDateTime dateTime) {
		ArrayList<Device> filteredListDevice = new ArrayList<>();
		ArrayList<Device> listDevices = (ArrayList<Device>) findAll();

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

	
	@Override
	public void checkAndSetTimestampsDevice(Device device) {

		if (device.getLastUpdate() == null)
			device.setLastUpdate(LocalDateTime.now());

		if (device.getRegistrationTime() == null)
			device.setRegistrationTime(LocalDateTime.now());
	}

	


}
