package com.tecnositaf.centrobackend.response;

import java.util.ArrayList;
import java.util.List;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;

public class GetDevicesResponse extends Response{

	
	private List<DTODevice> devices;
	private int size;
	
	public GetDevicesResponse(int code, String message, String path) {
		super(code, message, path);
		this.devices = null;
		this.size = 0;
	}
	
	public GetDevicesResponse(int code, String message, String path, List<Device> devices) {
		super(code, message, path);
		this.devices = new ArrayList<>();
		devices.forEach(device -> 
			this.devices.add( device.toDTODevice() )
		);
		this.size = devices.size();
	}
	
	
	public List<DTODevice> getDevices() {
		return devices;
	}
	public void setDevices(List<DTODevice> dtoDevices) {
		this.devices = dtoDevices;
	}
	public int getNumberOfDevices() {
		return size;
	}
	public void setNumberOfDevices(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "GetDevicesResponse {devices=" + devices + ", size=" + size + "}";
	}
	
}
