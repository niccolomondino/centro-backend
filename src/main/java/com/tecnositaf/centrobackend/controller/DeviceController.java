package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
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
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.response.GetDeviceByIdResponse;
import com.tecnositaf.centrobackend.response.GetDevicesResponse;
import com.tecnositaf.centrobackend.response.InsertNewDeviceResponse;
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
	 * @return ResponseEntity
	 */
	@GetMapping
	public ResponseEntity<Response> getDevices() {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						deviceService.getDevices()));
	}

	/**
	 * Read Device object by Id using HTTP Get request.
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Response> getDevice(@PathVariable("id") String idDevice) {

		if (StringUtility.isNullOrBlankString(idDevice)) 
			throw new FailureException("Id device cannot be null",Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);

		Device device = deviceService.getDeviceByIdNoOptional(idDevice);
		if (CommonsUtility.isNull(device))
			throw new ResourceNotFoundException("Device not found",Errors.RESULT_NOT_FOUND,HttpStatus.NOT_FOUND);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDeviceByIdResponse(0, "SUCCESS",
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
						device));
	}

	/**
	 * Read Device objects filtering by registration time, using HTTP GET request.
	 * Select objects with registration time equals or greater than timestamp.
	 * 
	 * @param timestamp
	 * @return ResponseEntity
	 */
	@GetMapping("/filter/time")
	public ResponseEntity<Response> filterDeviceByTime(
			@RequestParam(name = "ts") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestampDevice) {


		List<Device> filteredDevicesByTime = deviceService.filterDeviceByTime(timestampDevice);

		// non controllo niente perchè la lista puó essere vuota o null
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						filteredDevicesByTime));
	}

	/**
	 * 
	 * @param storageYear
	 * @return ResponseEntity
	 */
	@GetMapping("/storageYears/{year}")
	public ResponseEntity<Response> filterDevicesByStorageYear(@PathVariable("year") int storageYear) {

		// input validation : bad request (404)
		if (storageYear < 0)
			throw new FailureException("Storage year cannot be less than 0.",Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);

		// input validation : 200 OK
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						deviceService.filterDeviceByStorageYear(storageYear)));
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
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity<Response> createDevice(@RequestBody DTODevice dtoDevice) {
		
		logger.info("---------- POST /devices ----------");
		
		if (!StringUtility.isEmptyString(dtoDevice.getId()) || CommonsUtility.isNull(dtoDevice)) {
			throw new FailureException("Id should be null and Device cannot be null in POST request",
							Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);
		}
		
		Device device = dtoDevice.toDevice();

		deviceService.createDevice(device);
		logger.info("---------- New Device inserted ----------");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new InsertNewDeviceResponse(0, "SUCCESS", device,
						deviceService.getDevices()));
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
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteDevice(@PathVariable("id") String id) {
		if (StringUtility.isEmptyString(id)) {
			throw new FailureException("Id device cannot be null",Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);
		}

		deviceService.deleteDeviceById(id);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						deviceService.getDevices()));
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
	 * @return ResponseEntity
	 */
	@PutMapping
	public ResponseEntity<Response> updateDevice(@RequestBody DTODevice dtoDevice) {
		
		logger.info("---------- PUT /devices ----------");

		if (CommonsUtility.isNull(dtoDevice) || CommonsUtility.isNull(dtoDevice.getId())) {
			throw new FailureException("Device or id cannot be null in PUT requests", Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);
		}
		
		
		Device device = dtoDevice.toDevice();
	
		deviceService.updateDevice(device);
		
		logger.info("---------- Device updated ----------");
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						deviceService.getDevices()));

	}

	/**
	 * 
	 * @param id
	 * @param device
	 * @return ResponseEntity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateDevice(@PathVariable("id") String id, @RequestBody DTODevice dtoDevice) {

		if (CommonsUtility.isNull(dtoDevice) || !StringUtility.isEmptyString(dtoDevice.getId()) || StringUtility.isEmptyString(id))
			throw new FailureException("Device or id cannot be null in PUT requests", Errors.INVALID_FIELD,HttpStatus.BAD_REQUEST);
		
		Device device = dtoDevice.toDevice();

		deviceService.updateDeviceWithId(device, id);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(new GetDevicesResponse(0, "SUCCESS", 
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), 
						deviceService.getDevices()));

	}

}
