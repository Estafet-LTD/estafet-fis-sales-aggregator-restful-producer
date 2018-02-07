package com.estafet.fis.sales.aggregator.restful.producer.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;
import com.estafet.fis.sales.aggregator.restful.producer.service.ProductSalesBatchGenerator;

@Component
public class ProductSalesBatchGeneratorJob implements Job {

	@Autowired
	private ProductSalesBatchGenerator generator;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ProductSalesBatch batch = generator.generate();
		restTemplate.postForObject(System.getenv("RESTFUL_PRODUCER_SERVICE_URI") + "/batch",
				batch, ProductSalesBatch.class);
	}

}
