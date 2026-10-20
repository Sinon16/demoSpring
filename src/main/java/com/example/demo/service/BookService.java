package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	public Book create(Book book) {
		book.setId(null);
		return bookRepository.save(book);
	}

	public Optional<Book> update(Long id, Book book) {
		return bookRepository.findById(id).map(existing -> {
			existing.setTitle(book.getTitle());
			existing.setAuthor(book.getAuthor());
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
}
