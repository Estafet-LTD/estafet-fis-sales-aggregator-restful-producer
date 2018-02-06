package com.estafet.fis.sales.aggregator.restful.producer.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRODUCT_SALES_BATCH")
public class ProductSalesBatch {

	@Id
	@SequenceGenerator(name = "PRODUCT_SALES_BATCH_ID_SEQ", sequenceName = "PRODUCT_SALES_BATCH_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SALES_BATCH_ID_SEQ")
	@Column(name = "PRODUCT_SALES_BATCH_ID")
	private Integer batchId;

	@Column(name = "START_DATE", nullable = false, unique = true)
	private String startDate;

	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProductSale> productSales = new HashSet<ProductSale>();

	@JsonIgnore
	@OneToOne(mappedBy = "previous", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private ProductSalesBatch next;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "PRODUCT_SALES_BATCH_ID_SEQ", nullable = true, referencedColumnName = "PRODUCT_SALES_BATCH_ID")
	private ProductSalesBatch previous;

	public void setPrevious(ProductSalesBatch previous) {
		if (previous != null) {
			previous.next = this;
			this.previous = previous;
		}
	}

	public void addProductSale(ProductSale productSale) {
		productSale.setBatch(this);
		productSales.add(productSale);
	}

	public Integer getBatchId() {
		return batchId;
	}

	public String getStartDate() {
		return startDate;
	}

	public Set<ProductSale> getProductSales() {
		return productSales;
	}

	public ProductSalesBatch getNext() {
		return next;
	}

	public ProductSalesBatch getPrevious() {
		return previous;
	}

	public ProductSalesBatch init() {
		if (previous == null) {
			startDate = toCalendarString(newCalendar());
		} else {
			Calendar cal = toCalendar(previous.startDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);
			startDate = toCalendarString(cal);
		}
		for (ProductSale sale : productSales) {
			sale.init();
		}
		return this;
	}

	private Calendar toCalendar(String calendarString) {
		try {
			Calendar cal = newCalendar();
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(calendarString));
			return cal;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private String toCalendarString(Calendar calendar) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	private Calendar newCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

}
