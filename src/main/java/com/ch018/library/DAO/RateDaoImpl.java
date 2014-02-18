package com.ch018.library.DAO;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.Rate;

@Repository
public class RateDaoImpl implements RateDao {

		private final Logger logger = LoggerFactory.getLogger(RateDaoImpl.class);
		
		@Autowired
		SessionFactory factory;
		
		@Override
		public void save(Rate rate) {
			
			Session session = factory.getCurrentSession();
			
			session.save(rate);
			
		}
	
		@Override
		public Rate getRate(Person person, Book book) {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Rate.class);
			
			criteria.add(Restrictions.eq("person", person));
			criteria.add(Restrictions.eq("book", book));
			
			Rate rate = (Rate) criteria.uniqueResult();		
			return rate;
		}
	
		@Override
		public long getRatesCount() {
			
			Criteria criteria = factory.getCurrentSession().createCriteria(Rate.class);
			criteria.setProjection(Projections.rowCount());
			
			return (long) criteria.uniqueResult();
		}

	
}
