package edu.sjsu.cmpe.library.domain;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"isbn" , "title" , "publication-date" , "language" , "num-pages" , "status" , "reviews" , "authors"})
public class Book {
    private long isbn;
    @NotEmpty
    private String title;
    @NotEmpty
    private String publication_date;
    
   
    private String language;
   
    private int num_pages;
    
    private List<Author> author;
    private List<Review> review;
    private String status;
    // add more fields here
    @JsonProperty("num-pages")
    public int getNum_pages() {
		return num_pages;
	}
    @JsonProperty("num-pages")
	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}

	/**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }
    /**
     * @return the publication_date
     */
   
    @JsonProperty("publication-date")
    public String getPublication_date() {
		return publication_date;
	}
    
    @JsonProperty("publication-date")
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	/**
     * @return the language
     */
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
     * @return the title
     */
	
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
	
    public void setTitle(String title) {
	this.title = title;
    }
    
    @JsonProperty("authors")
 	public List<Author> getAuthor() {
		return author;
	}
    
    @JsonProperty("authors")
	public void setAuthor(List<Author> author) {
    	
    	
    	this.author = author;
	}
    @JsonProperty("status")
	public String getStatus() {
		return status;
	}
    
    @JsonProperty("status")
	public void setStatus(String status) {
    	
		this.status = status.toLowerCase();
	}
    
    @JsonProperty("reviews")
	public List<Review> getReview() {
		return review;
	}
    
    @JsonProperty("reviews")
	public void setReview(List<Review> review) {
		this.review = review;
	}
	
         	  
}
