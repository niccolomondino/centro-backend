package com.tecnositaf.centrobackend.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

import org.springframework.http.HttpStatus;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;

public class DateUtility {
	
	private DateUtility() {
	}
	
	public static int calculateYearsFromCurrentTime(int year) {
		return ( Calendar.getInstance().get(Calendar.YEAR) - year);
	}

	public static LocalDateTime parseStringToLocalDateTimeWithPattern(String timestamp, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		try {
			LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
			return dateTime;
		} catch (DateTimeParseException ex) { 
			throw new DateTimeParseException("Query string is not in the right format.",timestamp,-1);
		}
		
	}

	public static long getCurrentEpochTime(ZoneOffset zoneOffset) {
		LocalDateTime localDateTime = LocalDateTime.now(); //example: 1970-01-01T00:00:00Z
		Instant instant = localDateTime.toInstant(zoneOffset);
		long epochTimeInMillis = instant.toEpochMilli(); 
		return epochTimeInMillis;
	}


    public static Integer calculateAgeOf(LocalDate birthday){
        return DateUtility.calculateAgeOf(birthday, LocalDate.now());
    }
    
    public static Integer calculateAgeOf(LocalDate birthday, LocalDate today){
        if( birthday == null )      return null;
        if( today == null )         throw new FailureException(Errors.UNEXPECTED_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        Period period = Period.between(birthday, today);
        return period.getYears();
    }
}
