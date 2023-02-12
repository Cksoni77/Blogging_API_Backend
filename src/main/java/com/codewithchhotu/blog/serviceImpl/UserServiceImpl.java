package com.codewithchhotu.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithchhotu.blog.entities.User;
import com.codewithchhotu.blog.exception.ResourceNotFoundException;
import com.codewithchhotu.blog.payload.UserDto;
import com.codewithchhotu.blog.repository.UserRepo;
import com.codewithchhotu.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saved = this.userRepo.save(user);
		return this.userToDto(saved);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","ID",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User savedUser = this.userRepo.save(user);
		UserDto savedUserToDto = this.userToDto(savedUser);
		return savedUserToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","ID",userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> collectUsers = users.stream()
				.map(user->this.userToDto(user)).collect(Collectors.toList());
		return collectUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("User","ID",userId));
		this.userRepo.delete(user);		
	}
	
	private User dtoToUser(UserDto userdto) {
		 User user = this.modelMapper.map(userdto, User.class);
		
//		User user=new User();
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	
	private UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto=new UserDto();
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
