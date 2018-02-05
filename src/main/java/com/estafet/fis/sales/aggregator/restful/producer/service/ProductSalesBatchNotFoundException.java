package com.estafet.fis.sales.aggregator.restful.producer.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Next batch does not exist")  // 404
public class ProductSalesBatchNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductSalesBatchNotFoundException(int batchId) {
		super("no new batches after id " + batchId);
	}

	
	
}
