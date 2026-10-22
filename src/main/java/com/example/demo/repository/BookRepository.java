package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    @Query("SELECT DISTINCT b.author FROM Book b")
    List<String> findAllAuthors();

    // Soporte para Categorías (Strings)
    List<Book> findByCategories(String category);

    @Query("SELECT DISTINCT c FROM Book b JOIN b.categories c")
    List<String> findAllCategories();
}