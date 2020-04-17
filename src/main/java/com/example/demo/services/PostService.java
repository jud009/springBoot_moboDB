package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public Optional<Post> findById(String id) {
		Optional<Post> postId = postRepository.findById(id);

		if (postId.isEmpty()) {
			throw new ObjectNotFoundException("Object not found");
		}
		return postId;
	}

	public List<Post> findByTitle(String title) {
		return postRepository.searchTitle(title); 
	}
}
