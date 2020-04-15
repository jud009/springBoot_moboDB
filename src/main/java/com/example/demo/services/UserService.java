package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(String id) {
		Optional<User> userId = userRepository.findById(id);

		if (userId.isEmpty()) {
			throw new ObjectNotFoundException("Object not found");
		}
		return userId;
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User user) {
		Optional<User> newInfos = userRepository.findById(user.getId());
		updateData(newInfos.get(), user);
		return userRepository.save(newInfos.get());
	}

	private void updateData(User newInfos, User user) {
		newInfos.setName(user.getName());
		newInfos.setEmail(user.getEmail());
	}

	public User fromDto(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
}
