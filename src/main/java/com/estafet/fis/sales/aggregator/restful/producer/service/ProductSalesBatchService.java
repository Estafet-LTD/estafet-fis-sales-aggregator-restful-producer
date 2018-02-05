package com.estafet.fis.sales.aggregator.restful.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estafet.fis.sales.aggregator.restful.producer.dao.ProductSalesBatchDAO;
import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;

@Service
public class ProductSalesBatchService {

	@Autowired
	private ProductSalesBatchDAO productSalesBatchDAO;
		
	public ProductSalesBatch getInitialBatch() {
		return productSalesBatchDAO.getFirstBatch();
	}
	
	public ProductSalesBatch getNextBatch(int batchId) {
		ProductSalesBatch next = getBatch(batchId).getNext();
		if (next != null) {
			return next;
		} else {
			throw new NextProductSalesBatchNotFoundException(batchId);
		} 
	}
	
	public ProductSalesBatch getBatch(int batchId) {
		ProductSalesBatch batch =  productSalesBatchDAO.getBatchById(batchId);
		if (batch != null) {
			return batch;
		} else {
			throw new ProductSalesBatchNotFoundException(batchId);
		}
	}
	
}
