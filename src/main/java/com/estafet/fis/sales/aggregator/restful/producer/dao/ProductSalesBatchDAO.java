package com.estafet.fis.sales.aggregator.restful.producer.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.estafet.fis.sales.aggregator.restful.producer.model.ProductSalesBatch;

@Repository
public class ProductSalesBatchDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(ProductSalesBatch batch) {
		if (batch.getPrevious() == null) {
			entityManager.persist(batch);	
		} else {
			entityManager.merge(batch.getPrevious());	
		}
	}

	public ProductSalesBatch getFirstBatch() {
		if (batchExists()) {
			List<ProductSalesBatch> result = entityManager.createQuery(
					"select b from ProductSalesBatch b where b.batchId = (select min(bb.batchId) from ProductSalesBatch bb)",
					ProductSalesBatch.class).getResultList();
			if (!result.isEmpty()) {
				return result.get(0);
			}
		}
		return null;
	}

	public ProductSalesBatch getLastProductSalesBatch() {
		if (batchExists()) {
			List<ProductSalesBatch> result = entityManager.createQuery(
					"select b from ProductSalesBatch b where b.batchId = (select max(bb.batchId) from ProductSalesBatch bb)",
					ProductSalesBatch.class).getResultList();
			if (!result.isEmpty()) {
				return result.get(0);
			}
		}
		return null;
	}

	public ProductSalesBatch getBatchById(int batchId) {
		return entityManager.find(ProductSalesBatch.class, new Integer(batchId));
	}
	
	public boolean batchExists() {
		Query query = entityManager.createQuery("SELECT count(*) FROM ProductSalesBatch");
        return (long) query.getSingleResult() > 0; 
	}

}
