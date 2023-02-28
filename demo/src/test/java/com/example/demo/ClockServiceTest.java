package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.demo.application.ClockService;
import com.example.demo.application.CustomException;

import junit.framework.Assert;

public class ClockServiceTest {
	ClockService clkSrv = new ClockService();

	@Test
	public void test() throws CustomException {
		assertEquals("It's Twelve Forty Five", clkSrv.getTime("12:45"));
		assertEquals("It's midnight", clkSrv.getTime("00:00"));
		assertEquals("It's midday", clkSrv.getTime("12:00"));
	}
	
	@Test
	public void testException() throws CustomException {
		Exception exception = assertThrows(CustomException.class, () -> {
			clkSrv.getTime("120000");
	    });
		String expectedMessage = "Enter Proper time in 24 hours format. Sample: 08:24";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
