package com.ch018.library.util;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataModelContainer {

	@Autowired
	DriverManagerDataSource dataSource;
	
	private DataModel dataModel;
	
	private Recommender recommender;
	
	private CachingRecommender cachedRecommender;
	
	private final Logger logger = LoggerFactory.getLogger(DataModelContainer.class);
	
	public void initDataModel() {
		
		logger.info("DATASOURCE {}", dataSource);
		
		if(dataModel == null) {
		
			try {
				dataModel = new MySQLJDBCDataModel(dataSource, "rates", "pid", "bid", "score", "rate_date");
				
				UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		
				UserNeighborhood neighbor = new NearestNUserNeighborhood(2, similarity, dataModel);
		
				recommender = new GenericUserBasedRecommender(dataModel, neighbor, similarity);
				
				cachedRecommender = new CachingRecommender(recommender);
				
				logger.info("USers {}", cachedRecommender.getDataModel().getNumUsers());
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
	public void refresh() {
		cachedRecommender.refresh(null);
	}

	public CachingRecommender getCachedRecommender() {
		return cachedRecommender;
	}

	public void setCachedRecommender(CachingRecommender cachedRecommender) {
		this.cachedRecommender = cachedRecommender;
	}

	public DriverManagerDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DriverManagerDataSource dataSource) {
		this.dataSource = dataSource;
		logger.info("DATASOURCE SET = {}", this.dataSource);
	}
	
	
	
}
