package com.ch018.library.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genreTranslations")
public class GenreTranslations {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "gid")
	private int gid;
	
	/*@ManyToOne
	@JoinColumn(name = "genreId")
	private Genre genre;*/
	
	@Column(name = "genreId")
	private int genreId;
	
	@Column(name = "langId")
	private String lang;
	
	@Column(name = "value")
	private String genreTranslation;
	
	@ManyToMany(mappedBy = "genre")
	private Set<Book> book;
	
	public GenreTranslations(){
		
	}
	
	public GenreTranslations(int genreId, String language, String genreTranslation){
		this.genreId = genreId;
		this.lang = language;
		this.genreTranslation = genreTranslation;
	}
	
	public int getId() {
		return gid;
	}
	
	public void setId(int gid) {
		this.gid = gid;
	}
	
	public String getLanguage() {
		return lang;
	}
	
	public void setLanguage(String language) {
		this.lang = language;
	}
	
	public String getGenreTranslation() {
		return genreTranslation;
	}
	
	public void setGenreTranslation(String genreTranslation) {
		this.genreTranslation = genreTranslation;
	}
	
	public int getGenreId() {
		return genreId;
	}
	
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	
	/*public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}*/
	
	public Set<Book> getBooks() {
		return book;
	}
	
	public void setBooks(Set<Book> book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		
		return genreTranslation;
	}
}
