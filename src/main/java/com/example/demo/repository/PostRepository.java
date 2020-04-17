package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String title); 
	
	List<Post> findByTitleContainingIgnoreCase(String title);
	
	@Query(" { $and: [ {date: {$gte: ?1} }, { date: { $lte: ?2} },"
			+ " { $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, { 'body' : { $regex: ?0, $options: 'i' } }, { 'comments.text' : { $regex: ?0, $options: 'i' } }] } ] }")
	List<Post> fullQuery(String title, Date minDate, Date maxDate);  

}
