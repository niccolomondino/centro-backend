package com.tecnositaf.centrobackend.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.model.Device;

public class GetDeviceByIdResponse extends Response{

	private Device device;
	
	public GetDeviceByIdResponse(int code, String message, String path) {
		super(code, message, path);
	}

	public GetDeviceByIdResponse(Device device) {
		super(2,"Get Device by ID Response Successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.device = device;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}
	
}
