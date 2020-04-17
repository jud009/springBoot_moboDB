package com.example.demo.resources;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Post;
import com.example.demo.resources.util.URL;
import com.example.demo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService postService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Optional<Post> posts = postService.findById(id);
		return ResponseEntity.ok().body(posts.get());
	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String title) {
		title = URL.decodeParam(title);
		List<Post> list = postService.findByTitle(title);
		return ResponseEntity.ok().body(list);
	}
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullQuery(
			@RequestParam(value = "text", defaultValue = "") String title, 
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){
		title = URL.decodeParam(title); 
		Date min = URL.convertDate(minDate, new Date(0L)); 
		Date max = URL.convertDate(maxDate, new Date()); 
		List<Post> list = postService.fullQuery(title, min, max);
		return ResponseEntity.ok().body(list); 
	}
	

}
