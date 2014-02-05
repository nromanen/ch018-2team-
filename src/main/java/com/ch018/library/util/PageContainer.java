package com.ch018.library.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ch018.library.entity.Book;
import com.ch018.library.service.UniversalService;
import com.ch018.library.util.annotation.Between;
import com.ch018.library.util.annotation.ComplexType;
import com.ch018.library.util.annotation.SimpleSearch;

public class PageContainer<T> {

		private Logger logger = LoggerFactory.getLogger(PageContainer.class);
	
		private List<T> items;
		
		@Autowired
		private UniversalService<T> universalService;
		
		@Autowired
		private ReflectionUtils reflectionUtils;
		
		public List<T> getItemsPart(SearchParams searchParams, Class<T> clazz) {
			logger.info("items before {}", items);
			if(items == null)
				recalculate(searchParams, clazz);
			logger.info("items after {}", items);
			int pageNum = searchParams.getPage();
			int pageSize = searchParams.getPageSize();
			List<T> pageBooks = new ArrayList<>();
			int quantity = (int) Math.ceil((double) items.size() / pageSize);
			if(quantity == 0)
				quantity = 1;
			searchParams.setPagesQuantity(quantity);
			if(pageNum > quantity) {
				pageNum = 1;
				searchParams.setPage(pageNum);
			}
	
			int end = pageNum * pageSize;
			int start = end - pageSize;
			if(end > items.size())
				end = items.size();
	
			pageBooks = items.subList(start, end);
			return pageBooks;
		}
		
		
		public void recalculate(SearchParams searchParams, Class<T> clazz) {
			items = universalService.getPaginatedResult(searchParams, clazz);
		}
		
		public void recalculateLocal(SearchParams search, Class<T> entity) {
			
			
			orderLocale(search, entity);

			
		}
		
		private void orderLocale(SearchParams searchParams, final Class<T> clazz)  {
			logger.info("Local Sort start");
			if(items == null)
				recalculate(searchParams, clazz);
			final String field = searchParams.getOrderField();
			final Boolean order = searchParams.getOrder();

			Comparator<T> comparator = new Comparator<T>() {

				@Override
				public int compare(T item1, T item2)  {
					Field classField = null;
					Object o1 = null;
					Object o2 = null;
					try {
						classField = clazz.getDeclaredField(field);
						classField.setAccessible(true);
						o1 = classField.get(item1);
						o2 = classField.get(item2);
					} catch (Exception e) {
						logger.error("during locale sort {}", e);
					}
					if(o1 instanceof Comparable && o2 instanceof Comparable) {
						Comparable n1 = (Comparable) o1;
						Comparable n2 = (Comparable) o2;
						if(!order) {
							return n1.compareTo(n2);
						} else {
							return n2.compareTo(n1);
						}
					}
					return 0;
				}
			};

			Collections.sort(items, comparator);

		}


		public List<T> getItems() {
			return items;
		}


		public void setItems(List<T> items) {
			this.items = items;
		}
		
		
}
