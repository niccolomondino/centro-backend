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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inMaintenance == null) ? 0 : inMaintenance.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((registrationTime == null) ? 0 : registrationTime.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Device other = (Device) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inMaintenance == null) {
			if (other.inMaintenance != null)
				return false;
		} else if (!inMaintenance.equals(other.inMaintenance))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (registrationTime == null) {
			if (other.registrationTime != null)
				return false;
		} else if (!registrationTime.equals(other.registrationTime))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
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
