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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@SequenceGenerator(name = "PRODUCT_ID_SEQ", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_SEQ")
	@Column(name = "PRODUCT_ID")
	private int id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false, referencedColumnName = "PRODUCT_CATEGORY_ID")
	private ProductCategory category;

	@JsonIgnore
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductSale> sales = new ArrayList<ProductSale>();

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public ProductCategory getCategory() {
		return category;
	}

}
