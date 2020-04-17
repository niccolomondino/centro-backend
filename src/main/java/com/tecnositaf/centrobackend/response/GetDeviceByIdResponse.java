package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;

public class GetDeviceByIdResponse extends Response{

	private DTODevice device;
	
	public GetDeviceByIdResponse(int code, String message, String path) {
		super(code, message, path);
		this.device = null;
	}

	public GetDeviceByIdResponse(int code, String message, String path, Device device) {
		super(code, message, path);
		this.device = device.toDTODevice();
	}

	public DTODevice getDevice() {
		return device;
	}

	public void setDevice(DTODevice device) {
		this.device = device;
	}
	
}
