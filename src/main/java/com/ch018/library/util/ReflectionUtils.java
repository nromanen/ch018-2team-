package com.ch018.library.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.util.annotation.Affect;
import com.ch018.library.util.annotation.Between;
import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.Ordinary;
import com.ch018.library.util.annotation.SimpleSearch;

public class ReflectionUtils {

		private final Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
	
		@Autowired
		Switch switcher;
		
		public void fillCriteria(Criteria criteria, SearchParams search) {
			logger.info("DB Search start");
			SearchParams searchParams = null;
			try {
			Class<?> clazz = search.getClass().getSuperclass();
			searchParams = (SearchParams) clazz.newInstance();
			} catch (Exception e) {
				logger.error("during create newInstance {}", e.getMessage());
			} 
			searchParams.copy(search);
			List<Field> ordinaryFields = getOrdinaryFields(searchParams.getClass());
			List<Field[]> betweenFields = getBetweenFields(searchParams.getClass());
			List<Field> simpleSearchFields = getFieldsByAnnotation(searchParams.getClass(), SimpleSearch.class);
			List<Field> complexTypeFileds = getFieldsByAnnotation(searchParams.getClass(), ComplexType.class);
			
			
			for(Field field : simpleSearchFields) {
				try {
					field.setAccessible(true);
					Object o = field.get(searchParams);
					if(o != null) {
						List<SimpleExpression> simpleExps = new ArrayList<>();
						for(String entityField : field.getAnnotation(SimpleSearch.class).entityFields()) {
							simpleExps.add(Restrictions.like(entityField, (String) o, MatchMode.ANYWHERE));
						}
						Disjunction or = Restrictions.disjunction();
						for(SimpleExpression simpleExp : simpleExps)
							or.add(simpleExp);
						criteria.add(or);
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
						System.out.println("GENRE " + o + " FIELD " + entityField);
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
			
			Integer pageNum = search.getPage();
			Integer pageSize = search.getPageSize();
			int itemsQuantity = criteria.list().size();
			int quantity = (int) Math.ceil((double) itemsQuantity / pageSize);
			if(quantity == 0)
				quantity = 1;
			if(pageNum > quantity || pageNum <= 0) {
				pageNum = 1;
				search.setPage(pageNum);
			}
			search.setPagesQuantity(quantity);
			
			if(!switcher.getSwitcher()) {
				criteria.setFirstResult((pageNum - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			} 
				
			
		}
	
		
		public List<Field> getOrdinaryFields(Class<?> clazz) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> filteredFields = new ArrayList<>();
			for(Field field : allFields) {
				if(field.isAnnotationPresent(Ordinary.class) && !field.getAnnotation(Ordinary.class).onlyCopy()) {
					filteredFields.add(field);	
				}
			}
			
			
			return filteredFields;
		}
		
		
		public List<Field[]> getBetweenFields(Class<?> clazz) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field[]> filteredFields = new ArrayList<>();
			
			for(Field field : allFields) {
				if(field.isAnnotationPresent(Between.class)) {
	
					if(field.getAnnotation(Between.class).value().equals("start")) {
						Field[] returnFields = new Field[2];
						field.setAccessible(true);
						returnFields[0] = field;
						String FieldName = field.getAnnotation(Between.class).name();
						for(Field fieldEnd : allFields) {
							if(fieldEnd.isAnnotationPresent(Between.class) 
									&& fieldEnd.getAnnotation(Between.class).name().equals(FieldName) 
									&& fieldEnd.getAnnotation(Between.class).value().equals("end")) {
								returnFields[1] = fieldEnd;
								fieldEnd.setAccessible(true);
								filteredFields.add(returnFields);
								break;
							}
						}
					}
				}
					
				}
			
			return filteredFields;
		}
		
		public List<Field> getFieldsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) {
			Field[] allFields = clazz.getDeclaredFields();
			List<Field> filteredFields = new ArrayList<>();
			
			for(Field field : allFields) {
				if(field.isAnnotationPresent(annotation))
					filteredFields.add(field);
			}
			
			return filteredFields;
		}
}
