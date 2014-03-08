package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

	private final BookRepositoryInterface bookRepository;

	public ReviewResource(BookRepositoryInterface bookRepository) {
		this.bookRepository = bookRepository;
	}

	@POST
	@Timed(name = "create-review")
	public Response createReview(@PathParam("isbn") LongParam isbn,
			@Valid Review request) {

		Review reviewObject = bookRepository.createReview(isbn.get(), request);

		Book book = bookRepository.getBookByISBN(isbn.get());

		String location = "/books/" + book.getIsbn() + "/reviews/"
				+ reviewObject.getId();
		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-review", location, "GET"));

		return Response.status(200).entity(bookResponse).build();

	}

	@GET
	@Timed(name = "view-book-review")
	public Response viewAllBookReview(@PathParam("isbn") LongParam isbn,
			Review request) {

		Book book = bookRepository.getBookByISBN(isbn.get());

		ReviewDto reviewResponse = new ReviewDto(book.getReview());

		return Response.status(200).entity(reviewResponse).build();
	}

	@GET
	@Path("/{reviewId}")
	@Timed(name = "view-book-by-reviewId")
	public Response viewBookReview(@PathParam("isbn") LongParam isbn,
			@PathParam("reviewId") IntParam reviewId) {

		Book book = bookRepository.getBookByISBN(isbn.get());
		
		ReviewDto reviewResponse = null;
		List<Review> reviewList = book.getReview();

		List<Review> tempList = new ArrayList<Review>();
		for (Review reviewObj : reviewList) {

			if (reviewObj.getId() == reviewId.get())
				tempList.add(reviewObj);
				
		}
		reviewResponse = new ReviewDto(tempList);

		return Response.status(200).entity(reviewResponse).build();
	}
}
