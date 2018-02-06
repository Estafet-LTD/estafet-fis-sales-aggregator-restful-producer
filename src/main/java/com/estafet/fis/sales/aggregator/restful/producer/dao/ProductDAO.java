package com.estafet.fis.sales.aggregator.restful.producer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.estafet.fis.sales.aggregator.restful.producer.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Product> getProducts() {
		return entityManager.createQuery("select p from Product p", Product.class).getResultList();
	}
	
	public Product getProduct(int productId) {
		return entityManager.find(Product.class, new Integer(productId));
	}

}
