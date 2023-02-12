package com.codewithchhotu.blog.controller;

import java.util.List;
//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithchhotu.blog.payload.ApiResponse;
import com.codewithchhotu.blog.payload.UserDto;
import com.codewithchhotu.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);		
		return new ResponseEntity<UserDto>(createdUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@RequestBody UserDto userDto, @PathVariable Integer userId){
		UserDto userById = this.userService.getUserById(userId);
		return ResponseEntity.ok(userById);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAlluser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(new ApiResponse("User deleted sucessfully",true),HttpStatus.OK);
	}

}
