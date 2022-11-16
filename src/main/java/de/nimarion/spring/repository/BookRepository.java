package de.nimarion.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.nimarion.spring.beans.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {


}
