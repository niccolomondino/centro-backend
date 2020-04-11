package com.tecnositaf.centrobackend.utilities;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StringUtilityTest {

    @Test
    public void testTrueIsEmptyStringNullAsValue(){
        assertTrue( StringUtility.isEmptyString(null) );
    }
    @Test
    public void testTrueIsEmptyStringEmptyStringAsValue(){
        assertTrue( StringUtility.isEmptyString("") );
    }
    @Test
    public void testFalseIsEmptyString(){
        assertFalse( StringUtility.isEmptyString("test") );
    }
    
    @Test
    public void testTrueIsNullOrBlankStringNullAsValue(){
        assertTrue( StringUtility.isNullOrBlankString(null) );
    }
    @Test
    public void testTrueIsNullOrBlankStringStringAsValue(){
        assertTrue( StringUtility.isNullOrBlankString("") );
    }
    @Test
    public void testTrueIsNullOrBlankStringBlankStringAsValue(){
        assertTrue( StringUtility.isNullOrBlankString("    ") );
    }
    @Test
    public void testTrueIsNullOrBlankString(){
        assertFalse( StringUtility.isNullOrBlankString("test") );
    }
}
