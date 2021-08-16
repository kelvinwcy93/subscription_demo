package com.subs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.subs.demo.dto.SubscriptionIn;
import com.subs.demo.dto.SubscriptionOut;
import com.subs.demo.service.SubscriptionService;

@Controller
public class SubscriptionController {

	@Autowired
	private SubscriptionService serv;
	
	@PostMapping("/generateInvoiceDate")
	@ResponseBody
	public SubscriptionOut sayHello(@RequestBody SubscriptionIn in) {
		return serv.generateInvoiceDate(in);
	}
	
	@ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<String> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
