package com.tecnositaf.centrobackend.controller;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.utilities.DateUtility;

@RestController
@RequestMapping("/info")
public class InformationController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping(value = "/log")
    public String index() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
 
        return "Howdy! Check out the Logs to see the output...";
    }
	
	@GetMapping("/time/now")
	public Timestamp getTimeNow() {
		return new Timestamp(DateUtility.getCurrentEpochTime(ZoneOffset.UTC));
	}
	
	@GetMapping("/list/random")
	public List<Integer> getRandomListNumber() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			Random r = new Random();
			Integer in = (r.nextInt() * (10000) + 1);
			list.add(in);
		}
	    return list; 
	}
}
