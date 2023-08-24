package com.cassandratest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cassandratest.entity.Book;
import com.cassandratest.repo.BookRepository;
import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BookController {

	@Autowired 
    BookRepository repository;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(){
		return new ResponseEntity<List<Book>>(repository.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("/addbook")
	public ResponseEntity<Book> addBook(@RequestBody Book book){
		log.info("BookController.addBook() start");
		try {
			Book _book = repository.save(new Book(Uuids.timeBased(), book.getTitle(), book.getDescription(), book.getAuthor()));
			log.info("BookController.addBook() data saved in cassandra db with id {}",_book.getId());
			return new ResponseEntity<>(_book,HttpStatus.CREATED);
		}catch(Exception ex) {
			log.error("BookController.addBook() error occurred {}",ex.getCause());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
