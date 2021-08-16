package com.subs.demo.service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.subs.demo.dto.SubscriptionIn;
import com.subs.demo.dto.SubscriptionOut;
import com.subs.demo.type.SubscriptionType;

@Service
public class SubscriptionService {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public SubscriptionOut generateInvoiceDate(SubscriptionIn in) {

		String day= in.getDay();
		LocalDate startDate = LocalDate.parse(in.getStartDate(), formatter);
		LocalDate endDate = LocalDate.parse(in.getEndDate(), formatter);
		
		SubscriptionType type = SubscriptionType.valueOf(in.getType());
		
		List<String> invoiceDate = getInvoiceDate(type,day,startDate,endDate);
		
		SubscriptionOut out =  new SubscriptionOut();
		out.setAmount(in.getAmount());
		out.setType(in.getType());
		out.setInvoiceDates(invoiceDate);
		
		return out;
	}
	
	private List<String> getInvoiceDate(SubscriptionType type, String day, LocalDate startDate, LocalDate endDate) {
		List <String> invoiceDateList = new ArrayList<>();
		LocalDate nextCycleDate = LocalDate.now();
		
		//set maximum time span within 3 month
		if(startDate.plusMonths(3).compareTo(endDate)<0) {
			endDate = startDate.plusMonths(3);
		}
		
		//get first cycle date
		switch(type) {
		case DAILY:
			nextCycleDate = startDate;
			break;
		case WEEKLY:
			nextCycleDate = startDate.with(TemporalAdjusters.next(DayOfWeek.valueOf(day)));
			break;
		case MONTHLY:
			LocalDate dayofMonth = startDate.withDayOfMonth(Integer.parseInt(day));
			if(dayofMonth.compareTo(startDate)<0) {
				dayofMonth = dayofMonth.plusMonths(1);
			}
			nextCycleDate = dayofMonth;
			break;
		default:
		}
		
		Boolean hasNextCycle = true;
		
		do {
			String nextCycleDateStr = nextCycleDate.format(formatter);
			invoiceDateList.add(nextCycleDateStr);
			
			//get next cycle date
			switch(type) {
			case DAILY:
				nextCycleDate = nextCycleDate.plusDays(1);
				break;
			case WEEKLY:
				nextCycleDate = nextCycleDate.plusWeeks(1);
				break;
			case MONTHLY:
				nextCycleDate = nextCycleDate.plusMonths(1);
				break;
			default:
			}
			
			if(nextCycleDate.compareTo(endDate) > 0) {
				hasNextCycle = false;
			}
			
		}while(hasNextCycle);
		
		return invoiceDateList;
	}
}
