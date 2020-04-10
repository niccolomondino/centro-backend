package com.tecnositaf.centrobackend.response;

import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.model.Device;

public class GetDevicesResponse extends Response{

	
	private List<Device> devices;
	private int numberOfDevices;
	
	public GetDevicesResponse(int code, String message, String path,List<Device> devices) {
		super(code, message,path);
		this.devices = devices;
	}
	
	public GetDevicesResponse(List<Device> devices) {
		super(1,"Get Devices Response Successful",ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.devices = devices;
		//giusto settarlo qua?
		this.numberOfDevices = devices.size();
	}
	
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public int getNumberOfDevices() {
		return numberOfDevices;
	}
	public void setNumberOfDevices(int numberOfDevices) {
		this.numberOfDevices = numberOfDevices;
	}
	
	
	
}
