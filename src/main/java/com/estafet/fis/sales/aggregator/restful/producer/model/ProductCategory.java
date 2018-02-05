package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {

	@Id
	@Column(name = "CATRGORY_CODE")
	private String categoryCode;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<Product>();

	public ProductCategory setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
		return this;
	}

	public ProductCategory setDescription(String description) {
		this.description = description;
		return this;
	}

}
