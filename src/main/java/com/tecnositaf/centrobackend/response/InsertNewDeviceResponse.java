package com.tecnositaf.centrobackend.response;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;

public class InsertNewDeviceResponse extends Response{

		private DTODevice deviceInserted;
		private ArrayList<DTODevice> devices;	
		private int size;
		
		public InsertNewDeviceResponse(int code, String message) {
			super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
			this.deviceInserted = null;
			this.devices = null;
			this.size = 0;
		}

		public InsertNewDeviceResponse(int code, String message, Device deviceInserted, List<Device> devices, int size) {
			super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
			this.deviceInserted = deviceInserted.toDTODevice();
			this.devices = new ArrayList<>();	
			devices.forEach(device -> 
				this.devices.add( device.toDTODevice() )
			);
			this.size = size;
		}
		public InsertNewDeviceResponse(int code, String message, Device deviceInserted, List<Device> devices) {
			super(code, message, ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
			this.deviceInserted = deviceInserted.toDTODevice();
			this.devices = new ArrayList<>();
			devices.forEach(device -> 
				this.devices.add( device.toDTODevice() )
			);
			this.size = devices.size();
		}

		
		
		public DTODevice getDeviceInserted() {
			return deviceInserted;
		}

		public void setDeviceInserted(DTODevice deviceInserted) {
			this.deviceInserted = deviceInserted;
		}

		public List<DTODevice> getDevices() {
			return devices;
		}

		public void setDevices(List<DTODevice> devices) {
			this.devices = (ArrayList<DTODevice>) devices;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		
		
		@Override
		public String toString() {
			return "InsertNewDeviceResponse {DeviceInserted=" + deviceInserted + ", devices=" + devices + ", size=" + size + "}";
		}
	}
