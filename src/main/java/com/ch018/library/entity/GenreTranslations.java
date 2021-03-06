package com.ch018.library.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genretranslations")
public class GenreTranslations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482636637014819639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "genreId")
	private Genre genre;
	
	private String description;
	
	private String locale;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "GenreTranslations [id=" + id + ", genre=" + genre.getId()
				+ ", description=" + description + ", locale=" + locale + "]";
	}

	
	
	
	
	
}
