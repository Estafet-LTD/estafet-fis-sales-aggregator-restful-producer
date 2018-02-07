package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory {

	@Id
	@SequenceGenerator(name = "PRODUCT_CATEGORY_ID_SEQ", sequenceName = "PRODUCT_CATEGORY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_CATEGORY_ID_SEQ")
	@Column(name = "PRODUCT_CATEGORY_ID")
	private String id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<Product>();

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
