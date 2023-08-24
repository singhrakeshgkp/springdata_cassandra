package com.cassandratest.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.cassandratest.entity.Book;

@Repository
public interface BookRepository extends CassandraRepository<Book, UUID>{

	@AllowFiltering
	List<Book> findByAuthor();
	
}
