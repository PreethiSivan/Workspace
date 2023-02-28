package com.example.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clock")
public class ClockController {
	
	@Autowired
	private ClockService clockService;
	
	@GetMapping("/{time}")
	public ResponseEntity<String> getTimeText(@PathVariable("time") String time) throws CustomException{
		return ResponseEntity.status(HttpStatus.OK).body(clockService.getTime(time));
	}

}
