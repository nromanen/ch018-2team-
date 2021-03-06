package com.ch018.library.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "wishlist", 
        uniqueConstraints = { @UniqueConstraint( columnNames = { "personId", "bookId" } ) })
public class WishList implements Serializable {

		
	private static final long serialVersionUID = -3744895473210175797L;

		@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @ManyToOne
        @JoinColumn(name = "personId", referencedColumnName = "personId")
        private Person person;

        @ManyToOne
        @JoinColumn(name = "bookId", referencedColumnName = "bookId")
        private Book book;

        public WishList() {

        }

        public WishList(Person person, Book book) {
            this.person = person;
            this.book = book;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Person getPerson() {
            return person;
        }

        public void setPerson(Person person) {
            this.person = person;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((book == null) ? 0 : book.hashCode());
			result = prime * result + id;
			result = prime * result
					+ ((person == null) ? 0 : person.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof WishList))
				return false;
			WishList other = (WishList) obj;
			if (book == null) {
				if (other.book != null)
					return false;
			} else if (!book.equals(other.book))
				return false;
			if (id != other.id)
				return false;
			if (person == null) {
				if (other.person != null)
					return false;
			} else if (!person.equals(other.person))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "WishList [id=" + id + ", person=" + person.getPid() + ", book="
					+ book.getbId() + "]";
		}

        
        
}
