package com.ch018.library.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "genre")
public class Genre implements Serializable {

		
		private static final long serialVersionUID = -4110646287693953385L;

		@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
       
		@Transient
        private String description;
        
        @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
        private Set<Book> books;

        @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
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
			return "Genre [id=" + id + ", description=" + description + "]";
		}

        
        

    
}
