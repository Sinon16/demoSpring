package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(Book book) {
        validarCategoriasExistentes(book);
        book.setId(null);
        return bookRepository.save(book);
    }

    public Optional<Book> update(Long id, Book book) {
        validarCategoriasExistentes(book);
        return bookRepository.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            existing.setCategories(book.getCategories());
            return bookRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }



    public List<String> findAllAuthors() {
        return bookRepository.findAllAuthors();
    }

    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

   

    public List<String> findAllCategories() {
        return bookRepository.findAllCategories();
    }

 
    public List<Book> findByCategory(String category) {
        return bookRepository.findByCategories(category);
    }



    private void validarCategoriasExistentes(Book book) {
        if (book.getCategories() != null) {
            for (String categoryName : book.getCategories()) {
                if (!categoryService.existsByName(categoryName)) {
                    throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, 
                        "La categoría '" + categoryName + "' no existe. Créala primero."
                    );
                }
            }
        }
    }
}