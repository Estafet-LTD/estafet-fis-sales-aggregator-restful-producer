package com.estafet.fis.sales.aggregator.restful.producer.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.estafet.fis.sales.aggregator.restful.producer.service.ProductSalesBatchGenerator;
import com.estafet.fis.sales.aggregator.restful.producer.service.ProductSalesBatchService;

@Component
public class ProductSalesBatchGeneratorJob implements Job {

	@Autowired
	private ProductSalesBatchGenerator generator;
	
	@Autowired
	private ProductSalesBatchService service;
		
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		generator.generate();
		service.updateConsumer();
	}

}
