package com.estafet.fis.sales.aggregator.restful.producer.dao;

import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;

public class ProductSalesBatchDAO {

	public void save(ProductSalesBatch batch) {
		batch.init();
	}
	
	public ProductSalesBatch getFirstBatch() {
		return null;
	}
	
	public ProductSalesBatch getLastProductSalesBatch() {
		return null;
	}
	
	public ProductSalesBatch getBatchById(int batchId) {
		return null;
	}
	
	

}
