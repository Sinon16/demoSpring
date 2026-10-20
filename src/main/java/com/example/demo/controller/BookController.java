package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<Book> findAll() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		return ResponseEntity.of(bookService.findById(id));
	}

	@PostMapping
	public ResponseEntity<Book> create(@RequestBody Book book) {
		Book created = bookService.create(book);
		return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
		return ResponseEntity.of(bookService.update(id, book));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return bookService.delete(id)
				? ResponseEntity.noContent().build()
				: ResponseEntity.notFound().build();
	}
}
