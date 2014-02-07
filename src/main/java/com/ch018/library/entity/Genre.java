package com.ch018.library.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.i18n.LocaleContextHolder;


@Entity
@Table(name = "genres")
public class Genre implements Serializable {

		
       	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
       
        @Column(name = "description")
        private String description;
        
        @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
        private Set<Book> books;

        @OneToMany(mappedBy = "genre")
        private Set<GenreTranslations> translations;
        
        public Genre() {

        }

        public Genre(String description) {
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        
        public String getDescription() {
            return description;
        }
        
        public void setDescription(String description) {
            this.description = description;
        }
        
        
		public Set<Book> getBooks() {
			return books;
		}

		public void setBooks(Set<Book> books) {
			this.books = books;
		}
		
		

		public Set<GenreTranslations> getTranslations() {
			return translations;
		}

		public void setTranslations(Set<GenreTranslations> translations) {
			this.translations = translations;
		}

		@Override
        public boolean equals(Object other) {
            if (other == null) return false;
            if (other == this) return true;
            if (!(other instanceof Genre)) return false;
            Genre otherGenre = (Genre) other;
            return description.equals(otherGenre.getDescription());
        }

        @Override 
        public int hashCode() {
            return this.description.hashCode();
        }

      @Override
      public String toString() {
			//return "Genre [id=" + id + ", books=" + books + ", description="
			//		+ description + "]";

			return description;

		}

        

    
}
