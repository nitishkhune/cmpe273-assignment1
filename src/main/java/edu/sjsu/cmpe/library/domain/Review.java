package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Review {

	private int id;
	@NotNull
	private int rating;
	@NotNull
	private String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotNull
	public int getRating() {
		return rating;
	}
	@NotNull
	public void setRating(int rating) {
		this.rating = rating;
	}
	@NotNull
	public String getComment() {
		return comment;
	}
	@NotEmpty
	public void setComment(String comment) {
		this.comment = comment;
	}
	
		
}
