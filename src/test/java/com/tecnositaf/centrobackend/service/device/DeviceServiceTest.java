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
        assertSame(1, deviceService.getDevices().size());
    }

    @Test
    public void testGetDevicesValueOnInit(){
        List<Device> devicesActual = deviceService.getDevices();

        assertSame(1, devicesActual.size());
        
        Device device = new Device();
        device.setId("5e9726448ac3d90cd3ff6243");
        device.setType("Telecamera");
        device.setDescription("This is a telecamera");
        device.setBrand("Sony");
        //Testing dateTime considering 'manual' conversion to Zone Time
        device.setLastUpdate(LocalDateTime.of(2019, Month.AUGUST, 1,9,30,00));
        device.setRegistrationTime( LocalDateTime.of(2019, Month.AUGUST, 1,9,30,00) );
        device.setIsActive(true);
        device.setInMaintenance(false);
        device.setWeight(30.5);
        assert(device.equals(devicesActual.get(0)));
    }

    @Test
    public void testGetDevicesValueFailureOnInit(){
        List<Device> devicesExpected = Arrays.asList();
        assertNotSame(devicesExpected, deviceService.getDevices());
    }

}
