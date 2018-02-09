package com.estafet.fis.sales.aggregator.restful.producer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_SALES_BATCH_STATUS")
public class ProductSalesBatchStatus {

	@Id
	@Column(name = "PRODUCT_SALES_BATCH_STATUS_ID")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "SENT_PRODUCT_SALES_BATCH_ID", nullable = true, referencedColumnName = "PRODUCT_SALES_BATCH_ID")
	private ProductSalesBatch sent;

	public Integer getId() {
		return id;
	}

	public ProductSalesBatch getSent() {
		return sent;
	}

	public void setSent(ProductSalesBatch sent) {
		this.sent.setStatus(null);
		sent.setStatus(this);
		this.sent = sent;
	}

}
