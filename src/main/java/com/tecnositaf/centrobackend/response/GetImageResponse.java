package com.tecnositaf.centrobackend.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GetImageResponse extends Response{

	private byte[] file;
	private String filename;
	private long size;
	
	public GetImageResponse(int code, String message, String path) {
		super(code, message, path);
	}
	
	public GetImageResponse(byte[] file, String filename, long size) {
		super(0, "GetImageResponse successful", ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
		this.file = file;
		this.filename = filename;
		this.size = size;
	}

	public GetImageResponse(int code, String message, String path, byte[] file, String filename, long size) {
		super(code, message, path);
		this.file = file;
		this.filename = filename;
		this.size = size;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
}
