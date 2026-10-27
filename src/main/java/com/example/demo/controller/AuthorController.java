package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {

    private final BookService bookService;

    public AuthorController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<String> findAll() {
        return bookService.findAllAuthors();
    }

    @GetMapping("/{author}/books")
    public List<Book> findBooksByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }

    @PutMapping("/{author}")
    public ResponseEntity<Void> updateAuthor(
        @PathVariable String author, 
        @RequestBody AuthorUpdateRequest request) {
    
            boolean updated = bookService.updateAuthorName(author, request.newAuthor());
            return updated 
                    ? ResponseEntity.ok().build() 
                    : ResponseEntity.notFound().build();
        }
    @DeleteMapping("/{author}/books")
    public ResponseEntity<Void> delete(@PathVariable String author) {
        return bookService.deleteAuthorBook(author)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    public record AuthorUpdateRequest(String newAuthor) {}
}