package com.tecnositaf.centrobackend.service.device;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.service.DeviceService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;


    @Test
    public void testGetDevicesSizeOnInit(){
        assertSame(25, deviceService.getDevices().size());
    }

    @Test
    public void testGetDevicesValueOnInit(){
        List<Device> devicesActual = deviceService.getDevices();

        assertSame(25, devicesActual.size());

        Device sensor = new Device();
        sensor.setId("322");
        sensor.setType("Loris");
        sensor.setDescription("Cernich");
        sensor.setRegistrationTime( LocalDateTime.of(1991, Month.AUGUST, 13,12,12,12) );
        assert(sensor.equals(devicesActual.get(0)));
    }

    @Test
    public void testGetDevicesValueFailureOnInit(){
        List<Device> devicesExpected = Arrays.asList();
        assertNotSame(devicesExpected, deviceService.getDevices());
    }

}
