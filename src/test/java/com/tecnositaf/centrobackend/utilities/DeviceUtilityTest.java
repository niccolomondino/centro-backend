package com.tecnositaf.centrobackend.utilities;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.dto.DTODevice;
import com.tecnositaf.centrobackend.model.Device;
import com.tecnositaf.centrobackend.CentroBackendApplication;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DeviceUtilityTest {


    @Test
    public void testTrue() {
        DTODevice dtoDevice = new DTODevice("Telecamera", "This is a telecamera", "Sony", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        assertTrue( DeviceUtility.isValidForInsert(dtoDevice) );
    }

    @Test
    public void testFalseIdDeviceNotNull() {
        DTODevice dtoDevice = new DTODevice("Telecamera", "This is a telecamera", "Sony", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        dtoDevice.setId("0000aaaa");
        assertFalse( DeviceUtility.isValidForInsert(dtoDevice) );
    }
    @Test
    public void testFalseTypeNull() {
        DTODevice dtoDevice = new DTODevice(null, "This is a telecamera", "Sony", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        assertFalse( DeviceUtility.isValidForInsert(dtoDevice) );
    }
    @Test
    public void testFalseBrandNull() {
        DTODevice dtoDevice = new DTODevice("Telecamera", "This is a telecamera", null, LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        assertFalse( DeviceUtility.isValidForInsert(dtoDevice) );
    }
    @Test
    public void testFalseTypeEmptyString() {
        DTODevice dtoDevice = new DTODevice("", "This is a telecamera", "Sony", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        assertFalse( DeviceUtility.isValidForInsert(dtoDevice) );
    }
    @Test
    public void testFalseBrandEmptyString() {
        DTODevice dtoDevice = new DTODevice("Telecamera", "This is a telecamera", "", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5, 13 );
        assertFalse( DeviceUtility.isValidForInsert(dtoDevice) );
    }
    
    
    @Test
    public void testCheckAndSetTimestampsDeviceNoNull() {
        Device device = new Device("Telecamera", "This is a telecamera", "", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5);
        DeviceUtility.checkAndSetTimestampsDevice(device);
        assertNotNull(device.getLastUpdate());
        assertNotNull(device.getRegistrationTime());
    }
    @Test
    public void testCheckAndSetTimestampsDeviceLastUpdateNull() {
        Device device = new Device("Telecamera", "This is a telecamera", "", null, LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), true, false, 30.5);
        DeviceUtility.checkAndSetTimestampsDevice(device);
        assertNotNull(device.getLastUpdate());
        assertNotNull(device.getRegistrationTime());
    }
    @Test
    public void testCheckAndSetTimestampsDeviceRegistrationTimeNull() {
        Device device = new Device("Telecamera", "This is a telecamera", "", LocalDateTime.of(1991, Month.AUGUST, 13, 12, 10, 10), null, true, false, 30.5);
        DeviceUtility.checkAndSetTimestampsDevice(device);
        assertNotNull(device.getLastUpdate());
        assertNotNull(device.getRegistrationTime());
    }
    
}