package com.subs.demo.SubscriptionDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.subs.demo.dto.SubscriptionIn;
import com.subs.demo.dto.SubscriptionOut;
import com.subs.demo.service.SubscriptionService;
import com.subs.demo.type.SubscriptionType;

@SpringBootTest
class SubscriptionDemoApplicationTests {
	
	@Autowired
	private SubscriptionService serv;

	@Test
	void testSameAmount() {
		BigDecimal amount = new BigDecimal("123.00");
		SubscriptionIn in = new SubscriptionIn();
		in.setAmount(amount);
		in.setDay("1");
		in.setStartDate("01/01/2021");
		in.setEndDate("31/12/2021");
		in.setType("MONTHLY");
		
		SubscriptionOut out = serv.generateInvoiceDate(in);
		
	    assertEquals(amount, out.getAmount());
	}
	
	@Test
	void testSameType() {
		String type = "MONTHLY";
		SubscriptionIn in = new SubscriptionIn();
		in.setAmount(new BigDecimal("123.00"));
		in.setDay("1");
		in.setStartDate("01/01/2021");
		in.setEndDate("31/12/2021");
		in.setType(type);
		
		SubscriptionOut out = serv.generateInvoiceDate(in);
		
	    assertEquals(type, out.getType());
	}
	
	@Test
	void testDailySubscription() {
		
		LocalDate startDate = LocalDate.parse("01/01/2021", SubscriptionService.formatter);
		LocalDate endDate = LocalDate.parse("31/12/2021", SubscriptionService.formatter);
		
		List<String> invoiceDates = serv.getInvoiceDate(SubscriptionType.DAILY,"",startDate,endDate);
		//Total day count in 3 month  31+28+31 = 90
	    assertEquals(90, invoiceDates.size());
	}

	@Test
	void testWeeklySubscription() {
		
		LocalDate startDate = LocalDate.parse("01/01/2021", SubscriptionService.formatter);
		LocalDate endDate = LocalDate.parse("31/12/2021", SubscriptionService.formatter);
		
		List<String> invoiceDates = serv.getInvoiceDate(SubscriptionType.WEEKLY,"FRIDAY",startDate,endDate);
		//Total week count in 3 month  13
	    assertEquals(13, invoiceDates.size());
	}
	
	@Test
	void testMonthlySubscription() {
		
		LocalDate startDate = LocalDate.parse("01/01/2021", SubscriptionService.formatter);
		LocalDate endDate = LocalDate.parse("31/12/2021", SubscriptionService.formatter);
		
		List<String> invoiceDates = serv.getInvoiceDate(SubscriptionType.MONTHLY,"1",startDate,endDate);
		//Total month count in 3 month  3
	    assertEquals(3, invoiceDates.size());
	}

}
