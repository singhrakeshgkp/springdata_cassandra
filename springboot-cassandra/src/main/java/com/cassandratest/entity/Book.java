package com.cassandratest.entity;

import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table("book")
public class Book {

	@PrimaryKey
	UUID id;
	
	private String title;
	private String description;
	private String author;
	
}
