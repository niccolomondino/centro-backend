package com.tecnositaf.centrobackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.model.Device;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

}
