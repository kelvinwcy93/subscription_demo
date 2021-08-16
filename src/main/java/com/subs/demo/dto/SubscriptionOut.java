package com.subs.demo.dto;

import java.math.BigDecimal;
import java.util.List;

public class SubscriptionOut {

	private BigDecimal amount;
	
	private String type;
	
	private List<String> invoiceDates;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getInvoiceDates() {
		return invoiceDates;
	}

	public void setInvoiceDates(List<String> invoiceDates) {
		this.invoiceDates = invoiceDates;
	}
}
