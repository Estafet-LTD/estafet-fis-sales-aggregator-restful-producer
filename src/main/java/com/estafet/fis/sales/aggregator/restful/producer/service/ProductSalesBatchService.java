package com.estafet.fis.sales.aggregator.restful.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.estafet.fis.sales.aggregator.restful.producer.dao.ProductSalesBatchDAO;
import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;
import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatchStatus;

@Service
public class ProductSalesBatchService {

	@Autowired
	private ProductSalesBatchDAO productSalesBatchDAO;

	@Autowired
	private RestTemplate restTemplate;
		
	@Transactional(readOnly = true)
	public ProductSalesBatch getInitialBatch() {
		return productSalesBatchDAO.getFirstBatch();
	}
	
	@Transactional(readOnly = true)
	public ProductSalesBatch getNextBatch(int batchId) {
		ProductSalesBatch next = getBatch(batchId).getNext();
		if (next != null) {
			return next;
		} else {
			throw new NextProductSalesBatchNotFoundException(batchId);
		} 
	}
	
	@Transactional(readOnly = true)
	public ProductSalesBatch getBatch(int batchId) {
		ProductSalesBatch batch =  productSalesBatchDAO.getBatchById(batchId);
		if (batch != null) {
			return batch;
		} else {
			throw new ProductSalesBatchNotFoundException(batchId);
		}
	}
	
	@Transactional(readOnly = true)
	public void updateConsumer() {
		ProductSalesBatchStatus status = productSalesBatchDAO.getStatus();
		ProductSalesBatch sent = status.getSent() != null ? status.getSent() : productSalesBatchDAO.getFirstBatch();
		updateConsumer(sent);
	}
	
	private void updateConsumer(ProductSalesBatch sent) {
		if (sent.getNext() != null) {
			ProductSalesBatch next = sent.getNext();
			invokeConsumer(next);
			sent.getStatus().setSent(next);
			productSalesBatchDAO.save(next);
			updateConsumer(next);
		}
	}

	private void invokeConsumer(ProductSalesBatch next) {
		restTemplate.postForObject(System.getenv("RESTFUL_PRODUCER_SERVICE_URI") + "/batch",
				next, ProductSalesBatch.class);
	}
	
}
