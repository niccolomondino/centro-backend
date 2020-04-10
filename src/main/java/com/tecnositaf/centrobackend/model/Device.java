package com.tecnositaf.centrobackend.model;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.utilities.DateUtility;

/**
 * @author niccolo mondino
 */
@Document(collection = "devices")
public class Device {

	@Id
	private String id;
	private String type;
	private String description;
	private String brand;
	private Boolean isActive;
	private Boolean inMaintenance;
	private Double weight;
	// @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastUpdate;
	// @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registrationTime;

	public Device() {

	}

	public Device(String id) {
		this.id = id;
	}

	@PersistenceConstructor
	public Device(String type, String description, String brand, LocalDateTime lastUpdate,
			LocalDateTime registrationTime, Boolean isActive, Boolean inMaintenance, Double weight) {
		this.type = type;
		this.description = description;
		this.brand = brand;
		this.lastUpdate = lastUpdate;
		this.registrationTime = registrationTime;
		this.weight = weight;
		this.isActive = isActive;
		this.inMaintenance = inMaintenance;
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getInMaintenance() {
		return inMaintenance;
	}

	public void setInMaintenance(Boolean inMaintenance) {
		this.inMaintenance = inMaintenance;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", type=" + type + ", description=" + description + ", brand=" + brand
				+ ", lastUpdate=" + lastUpdate + ", registrationTime=" + registrationTime + ", isActive=" + isActive
				+ ", inMaintenance=" + inMaintenance + ", weight=" + weight + "]";
	}

	/*
	 * Method to transform Device model into DTODevice 
	 */
	public DTODevice toDTODevice() {
		DTODevice output = new DTODevice(); // 'this' => Device 'output'=> DTODevice
		BeanUtils.copyProperties(this, output); // vengono settate in 'output' tutti campi che hanno lo stesso nome tra
												// la classe Device e DTODevice

		/*** 'storageYears' of DTO class is a value calculated from 'registrationTime' ***/
		Integer storageYears = DateUtility.calculateYearsFromCurrentTime(this.getRegistrationTime().getYear());
		output.setStorageYears(storageYears);

		return output;
	}

}
