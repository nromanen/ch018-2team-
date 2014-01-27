package com.ch018.library.helper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SimpleSearchQuery{

		private String query = "";
		
		public String getQuery() {
			return query;
		}
	
		public void setQuery(String query) {
			this.query = query;
		}
		
		public void set(SimpleSearchQuery query) {
			this.query = query.query;
		}
	
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((query == null) ? 0 : query.hashCode());
			return result;
		}
	
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof SimpleSearchQuery))
				return false;
			SimpleSearchQuery other = (SimpleSearchQuery) obj;
			if (query == null) {
				if (other.query != null)
					return false;
			} else if (!query.equals(other.query))
				return false;
			return true;
		}
	
		@Override
		public String toString() {
			return "SimpleSearchQuery [query=" + query + "]";
		}
	
	
	
}
