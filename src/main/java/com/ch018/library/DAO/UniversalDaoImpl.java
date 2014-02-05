package com.ch018.library.DAO;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch018.library.util.ReflectionUtils;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.SearchParamsBook;
import com.ch018.library.util.annotation.Between;
import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.SimpleSearch;

@Repository
public class UniversalDaoImpl<T> implements UniversalDao<T> {

	
	private final Logger logger = LoggerFactory.getLogger(UniversalDao.class);
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	ReflectionUtils reflectionUtils;
	
	@Override
	public List<T> getPaginatedResult(SearchParams search, Class<?> entity) {

		SearchParamsBook searchParams = new SearchParamsBook();
		searchParams.copy(search);
		logger.info("after copy {}", searchParams);
		Class<?> clazz = entity;
		Criteria criteria = factory.getCurrentSession().createCriteria(clazz);
		
		
		List<Field> ordinaryFields = reflectionUtils.getOrdinaryFields(searchParams.getClass());
		List<Field[]> betweenFields = reflectionUtils.getBetweenFields(searchParams.getClass());
		List<Field> simpleSearchFields = reflectionUtils.getFieldsByAnnotation(searchParams.getClass(), SimpleSearch.class);
		List<Field> complexTypeFileds = reflectionUtils.getFieldsByAnnotation(searchParams.getClass(), ComplexType.class);
		
		
		for(Field field : simpleSearchFields) {
			try {
				field.setAccessible(true);
				Object o = field.get(searchParams);
				if(o != null) {
					for(String entityField : field.getAnnotation(SimpleSearch.class).entityFields()) {
						criteria.add(Restrictions.like(entityField, (String) o, MatchMode.ANYWHERE));
					}
				}
			} catch (Exception e) {
				logger.error("during update {} ", e.getMessage());
			}
		}
		
		for(Field field : ordinaryFields) {
			try {
				field.setAccessible(true);
				Object o = field.get(searchParams);
				if(o != null) {
					if(o instanceof String) {
						criteria.add(Restrictions.like(field.getName(), (String) o, MatchMode.ANYWHERE));
					} else {
						criteria.add(Restrictions.eq(field.getName(), o));
					}
				}
			} catch (Exception e) {
				logger.error("during update {} ", e.getMessage());
			}
		}

		for(Field field : complexTypeFileds) {
			try {
				field.setAccessible(true);
				Object o = field.get(searchParams);
				if(o != null && ((int) o) != 0) {
					String entityField = field.getAnnotation(ComplexType.class).entityField();
					
					criteria.add(Restrictions.eq(field.getName() + "." + entityField , o));
				}
			} catch (Exception e) {
				logger.error("during update {} ", e.getMessage());
			}
		}
		
		for(Field[] fields : betweenFields) {
			try {
				fields[0].setAccessible(true);
				fields[1].setAccessible(true);
				Object o1 = fields[0].get(searchParams);
				Object o2 = fields[1].get(searchParams);
				String annotationName = fields[0].getAnnotation(Between.class).name();
				if(o1 != null && o2 != null) {
					criteria.add(Restrictions.between(annotationName, o1, o2));
				}
			} catch (Exception e) {
				logger.error("during beetween fields {}", e.getMessage());
			}
		}
		
		if(search.getOrder()) 
			criteria.addOrder(Order.desc(search.getOrderField()));
		else 
			criteria.addOrder(Order.asc(search.getOrderField()));
		
		int pageNum = search.getPage();
		int pageSize = search.getPageSize();
		int itemsQuantity = criteria.list().size();
		int quantity =  itemsQuantity / pageSize;
		if(quantity == 0)
			quantity = 1;
		search.setPagesQuantity(quantity);
		criteria.setFirstResult((pageNum - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		
		
		return criteria.list();
	}

	
}
