package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCT_SALE")
public class ProductSale {

	@JsonIgnore
	@Id
	@SequenceGenerator(name = "PRODUCT_SALE_ID_SEQ", sequenceName = "PRODUCT_SALE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SALE_ID_SEQ")
	@Column(name = "PRODUCT_SALE_ID")
	private Integer id;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", nullable = false, referencedColumnName = "PRODUCT_ID")
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PRODUCT_SALES_BATCH_ID", nullable = false, referencedColumnName = "PRODUCT_SALES_BATCH_ID")
	private ProductSalesBatch batch;

	@Column(name = "SOLD", nullable = false)
	private int sold;

	@JsonGetter
	public int productId() {
		return product.getId();
	}
	
	@JsonGetter
	public int getSold() {
		return sold;
	}

	public ProductSale setBatch(ProductSalesBatch batch) {
		this.batch = batch;
		return this;
	}

	public ProductSale setProduct(Product product) {
		this.product = product;
		return this;
	}

	ProductSale init() {
		this.sold = ThreadLocalRandom.current().nextInt(1, 100); 
		return this;
	}

}
