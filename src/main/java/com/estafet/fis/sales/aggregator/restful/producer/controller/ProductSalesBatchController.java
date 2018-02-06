package com.estafet.fis.sales.aggregator.restful.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;
import com.estafet.fis.sales.aggregator.restful.producer.service.ProductSalesBatchService;

@RestController
public class ProductSalesBatchController {

	@Autowired
	private ProductSalesBatchService productSalesBatchService;
	
	@GetMapping("/batch")
	public ProductSalesBatch getInitialBatch() {
		return productSalesBatchService.getInitialBatch();
	}
	
	@GetMapping("/batch/{id}/next")
	public ProductSalesBatch getNextBatch(@PathVariable int id) {
		return productSalesBatchService.getNextBatch(id);
	}
	
	@GetMapping("/batch/{id}")
	public ProductSalesBatch getBatch(@PathVariable int id) {
		return productSalesBatchService.getBatch(id);
	}
	
}
