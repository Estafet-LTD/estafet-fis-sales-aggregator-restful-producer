package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductSale {

	@Id
	@SequenceGenerator(name = "PROJECT_SALE_ID_SEQ", sequenceName = "PROJECT_SALE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECT_SALE_ID_SEQ")
	@Column(name = "PROJECT_SALE_ID")
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false, referencedColumnName = "PRODUCT_ID")
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PROJECT_SALES_BATCH_ID", nullable = false, referencedColumnName = "PROJECT_SALES_BATCH_ID")
	private ProductSalesBatch batch;

	@Column(name = "SOLD", nullable = false)
	private int sold;

	public ProductSale setProduct(Product product) {
		this.product = product;
		return this;
	}

	ProductSale init() {
		this.sold = ThreadLocalRandom.current().nextInt(1, 10000); 
		return this;
	}

}
