package de.nimarion.spring.dto;

import javax.validation.constraints.NotEmpty;

import de.nimarion.spring.validator.ISBN;

public class BookDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    @ISBN
    private String isbn;


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    
    
    
    
}
