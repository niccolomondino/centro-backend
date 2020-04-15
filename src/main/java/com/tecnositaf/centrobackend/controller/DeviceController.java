package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.response.GetDeviceByIdResponse;
import com.tecnositaf.centrobackend.response.GetDevicesResponse;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.DeviceService;
import com.tecnositaf.centrobackend.utilities.CommonsUtility;
import com.tecnositaf.centrobackend.utilities.StringUtility;

/**
 * DeviceController is a REST Controller that implements CRUD functionalities
 * for the domain Device
 * 
 * @author niccolo mondino
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/devices")
public class DeviceController {

	@Autowired
	DeviceService deviceService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * ************************************************************************** *
	 * ****************************** GET REQUESTS ****************************** *
	 * ************************************************************************** *
	 */

	/**
	 * Read all Device objects using HTTP Get request.
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Response> getDevices() {
		return ResponseEntity.status(HttpStatus.OK).body(new GetDevicesResponse(deviceService.getDevices()));
	}

	/**
	 * Read Device object by Id using HTTP Get request.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Response> getDevice(@PathVariable("id") String idDevice) {

		if (StringUtility.isEmptyString(idDevice)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "idDevice cannot be null.",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		Device device = deviceService.getDeviceByIdNoOptional(idDevice);
		if (CommonsUtility.isNull(device))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(-1, "Device not found.",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		return ResponseEntity.status(HttpStatus.OK).body(new GetDeviceByIdResponse(device));
	}

	/**
	 * Read Device objects filtering by registration time, using HTTP GET request.
	 * Select objects with registration time equals or greater than timestamp.
	 * 
	 * @param timestamp
	 */
	@GetMapping("/filter/time")
	public ResponseEntity<Response> filterDeviceByTime(
			@RequestParam(name = "ts") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestampDevice) {


		List<Device> filteredDevicesByTime = deviceService.filterDeviceByTime(timestampDevice);

		// non controllo niente perchè la lista puó essere vuota o null

		return ResponseEntity.status(HttpStatus.OK).body(new GetDevicesResponse(filteredDevicesByTime));
	}

	/**
	 * 
	 * @param storageYear
	 * @return
	 */
	@GetMapping("/storageYears/{year}")
	public ResponseEntity<Response> filterDevicesByStorageYear(@PathVariable("year") int storageYear) {

		// input validation : bad
		if (storageYear < 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(-1, "Storage year cannot be less than 0.",
							ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		// input validation : 200 OK
		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetDevicesResponse(deviceService.filterDeviceByStorageYear(storageYear)));
	}

	/*
	 * ************************************************************************** *
	 * ***************************** POST REQUESTS ****************************** *
	 * ************************************************************************** *
	 */

	/**
	 * Create new Device using HTTP Post request.
	 * 
	 * @param device
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Response> createDevice(@RequestBody DTODevice dtoDevice) {
		
		logger.info("---------- POST /devices ----------");
		
		if (!StringUtility.isEmptyString(dtoDevice.getId()) || CommonsUtility.isNull(dtoDevice)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(-1, "Id should be null and Device cannot be null in POST request.",
							ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}
		
		Device device = dtoDevice.toDevice();

		deviceService.createDevice(device);
		logger.info("---------- New Device inserted ----------");

		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetDevicesResponse((ArrayList<Device>) deviceService.getDevices()));
	}

	/*
	 * ************************************************************************** *
	 * **************************** DELETE REQUESTS ***************************** *
	 * ************************************************************************** *
	 */

	/**
	 * Delete Device using HTTP Delete request.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteDevice(@PathVariable("id") String id) {
		if (StringUtility.isEmptyString(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "Id cannot be null :" + id,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}

		deviceService.deleteDeviceById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new GetDevicesResponse(deviceService.getDevices()));
	}

	/*
	 * ************************************************************************** *
	 * ***************************** PUT REQUESTS ******************************* *
	 * ************************************************************************** *
	 */

	/**
	 * Update Device using HTTP Delete request.
	 * 
	 * @param device
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Response> updateDevice(@RequestBody DTODevice dtoDevice) {
		
		logger.info("---------- PUT /devices ----------");

		if (CommonsUtility.isNull(dtoDevice) || CommonsUtility.isNull(dtoDevice.getId())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(-1, "Device or id cannot be null :",
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}
		
		
		Device device = dtoDevice.toDevice();
	
		deviceService.updateDevice(device);
		
		logger.info("---------- Device updated ----------");
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetDevicesResponse((ArrayList<Device>) deviceService.getDevices()));

	}

	/**
	 * 
	 * @param id
	 * @param device
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateDevice(@PathVariable("id") String id, @RequestBody DTODevice dtoDevice) {

		if (CommonsUtility.isNull(dtoDevice) || !StringUtility.isEmptyString(dtoDevice.getId()) || StringUtility.isEmptyString(id)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response(-1, "Device or id cannot be null :" + id,
							ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}
		
		Device device = dtoDevice.toDevice();

		deviceService.updateDeviceWithId(device, id);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetDevicesResponse((ArrayList<Device>) deviceService.getDevices()));

	}

}
