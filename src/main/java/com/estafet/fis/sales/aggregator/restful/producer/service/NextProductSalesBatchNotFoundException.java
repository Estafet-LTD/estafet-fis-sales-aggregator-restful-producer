package com.estafet.fis.sales.aggregator.restful.producer.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such batch")  // 404
public class NextProductSalesBatchNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NextProductSalesBatchNotFoundException(int batchId) {
		super("There's no batch for id " + batchId);
	}

	
	
}
