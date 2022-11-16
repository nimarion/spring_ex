package de.nimarion.spring.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import de.nimarion.spring.beans.Book;
import de.nimarion.spring.beans.Review;
import de.nimarion.spring.dto.BookDto;
import de.nimarion.spring.dto.ReviewDto;
import de.nimarion.spring.repository.BookRepository;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Book addBook(@Valid @RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        logger.info("Add book: " + book.getTitle() + " by " + book.getAuthor());
        return bookRepository.save(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Book getBookById(@PathVariable int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT, produces = "application/json")
    public @ResponseBody Book updateBookById(@PathVariable int id, @Valid @RequestBody BookDto bookDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        logger.info("Update book with id " + id);
        return bookRepository.save(book);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody void deleteBookById(@PathVariable int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        logger.info("Delete book with id " + id);
        bookRepository.delete(book);
    }

    // get all reviews for a book
    @RequestMapping(value = "/books/{id}/reviews", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Set<Review> getReviewsByBookId(@PathVariable int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        System.out.println(book.getReviews().size());
        return book.getReviews();
    }

    @RequestMapping(value = "/books/{id}/reviews", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Book addReviewToBook(@PathVariable int id, @Valid @RequestBody ReviewDto reviewDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        Review review = new Review();
        review.setScore(reviewDto.getScore());
        review.setText(reviewDto.getText());
        review.setBook(book);
        book.getReviews().add(review);
        logger.info("Add review to book with id " + id);
        return bookRepository.save(book);
    }

}
