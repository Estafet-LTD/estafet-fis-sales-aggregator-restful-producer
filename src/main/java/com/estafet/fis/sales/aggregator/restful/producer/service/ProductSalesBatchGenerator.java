package com.estafet.fis.sales.aggregator.restful.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estafet.fis.sales.aggregator.restful.producer.dao.ProductDAO;
import com.estafet.fis.sales.aggregator.restful.producer.dao.ProductSalesBatchDAO;
import com.estafet.fis.sales.aggregator.restful.producer.model.Product;
import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSale;
import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;

@Service
public class ProductSalesBatchGenerator {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductSalesBatchDAO productSalesBatchDAO;

	@Transactional
	public void generate() {
		ProductSalesBatch newBatch = new ProductSalesBatch();
		for (Product product : productDAO.getProducts()) {
			newBatch.addProductSale(new ProductSale().setProduct(product));
		}
		newBatch.setPrevious(productSalesBatchDAO.getLastProductSalesBatch());
		productSalesBatchDAO.save(newBatch.init());
	}

}
