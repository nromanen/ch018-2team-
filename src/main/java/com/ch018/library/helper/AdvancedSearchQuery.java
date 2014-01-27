package com.ch018.library.helper;

public class AdvancedSearchQuery implements SearchQuery {

		private String title;
		private String authors;
		private String publisher;
		private int genreId;
		
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getAuthors() {
			return authors;
		}
		public void setAuthors(String authors) {
			this.authors = authors;
		}
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		
		
		public int getGenreId() {
			return genreId;
		}
		public void setGenreId(int genreId) {
			this.genreId = genreId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((authors == null) ? 0 : authors.hashCode());
			result = prime * result
					+ ((publisher == null) ? 0 : publisher.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof AdvancedSearchQuery))
				return false;
			AdvancedSearchQuery other = (AdvancedSearchQuery) obj;
			if (authors == null) {
				if (other.authors != null)
					return false;
			} else if (!authors.equals(other.authors))
				return false;
			if (publisher == null) {
				if (other.publisher != null)
					return false;
			} else if (!publisher.equals(other.publisher))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "AdvancedSearchQuery [title=" + title + ", authors=" + authors
					+ ", publisher=" + publisher + "]";
		}
	
	
}
