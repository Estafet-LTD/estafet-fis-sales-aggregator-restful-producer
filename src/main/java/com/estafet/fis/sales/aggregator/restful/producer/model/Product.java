package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE", nullable = false, referencedColumnName = "CATEGORY_CODE")
	private ProductCategory category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductSale> sales = new ArrayList<ProductSale>();
	
}
