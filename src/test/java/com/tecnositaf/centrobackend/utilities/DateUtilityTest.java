package com.tecnositaf.centrobackend.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeParseException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runners.MethodSorters;

import com.tecnositaf.centrobackend.CentroBackendApplication;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateUtilityTest {

    @Test
    public void testSuccessCalculateYearsFromCurrentTime(){
        LocalDate day = LocalDate.of(2015, Month.APRIL, 4);
        assertSame( 5, DateUtility.calculateYearsFromCurrentTime(day.getYear()));

    }

    @Test
    public void testSuccessParseStringToLocalDateTimeWithPattern(){
    	String timestamp = "2012:12:12 16:12:12";
    	String pattern = "yyyy:MM:dd HH:mm:ss";
    	LocalDateTime ldt = LocalDateTime.of(LocalDate.of(2012, 12, 12), LocalTime.of(16, 12, 12));
    	assertEquals( ldt, DateUtility.parseStringToLocalDateTimeWithPattern(timestamp, pattern));
    }
    
    @Test
    public void testFailureParseStringToLocalDateTimeWithPatternWrongFormat(){
    	String timestamp = "16:12:31 - 2012:12:12";
    	String pattern = "yyyy:MM:dd HH:mm:ss";
    	assertThrows( DateTimeParseException.class, ()->DateUtility.parseStringToLocalDateTimeWithPattern(timestamp, pattern));
    }
    
    @Test
    public void testFailureParseStringToLocalDateTimeWithPatternNullValue(){
    	String timestamp = null;
    	String pattern = "yyyy:MM:dd HH:mm:ss";
    	assertThrows( NullPointerException.class, ()->DateUtility.parseStringToLocalDateTimeWithPattern(timestamp, pattern));
    }


}
