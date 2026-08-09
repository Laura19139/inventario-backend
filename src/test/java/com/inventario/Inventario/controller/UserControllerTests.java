package com.inventario.Inventario.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inventario.Inventario.model.LoginRequest;
import com.inventario.Inventario.model.LoginResponse;
import com.inventario.Inventario.model.User;
import com.inventario.Inventario.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Test
	public void Test_RegisterNewUser_SuccessFul() throws Exception {
		// Arrange
		User user = new User();
		user.setId(1);
		user.setUsername("laura");
		user.setPassword("123");
		user.setName("Laura");

		when(userService.register(user)).thenReturn(user);

		// Act
		User result = userController.register(user);

		// Assert
		assertEquals(1, result.getId());
		assertEquals("laura", result.getUsername());
		assertEquals("Laura", result.getName());
	}

	@Test
	public void Test_Login_SuccessFul() throws Exception {
		// Arrange
		LoginRequest request = new LoginRequest();
		request.setUsername("laura");
		request.setPassword("123");

		LoginResponse response = new LoginResponse(1, "laura", "Laura");

		when(userService.login("laura", "123")).thenReturn(response);

		// Act
		LoginResponse result = userController.login(request);

		// Assert
		assertEquals(1, result.getId());
		assertEquals("laura", result.getUsername());
		assertEquals("Laura", result.getName());
	}

	@Test
	public void Test_GetAllUsers_SuccessFul() {
		// Arrange
		User u1 = new User(1, "laura", "123", "Laura");
		User u2 = new User(2, "camilo", "1234", "Camilo");

		when(userService.getAllUsers()).thenReturn(Arrays.asList(u1, u2));

		// Act
		List<User> result = userController.getAllUsers();

		// Assert
		assertEquals(2, result.size());
	}

	@Test
	public void Test_GetUserById_SuccessFul() throws Exception {
		// Act
		User user = new User(1, "laura", "123", "Laura");

		when(userService.getUserById(1)).thenReturn(user);

		// Arrange
		User result = userController.getUserById(1);

		// Assert
		assertEquals(1, result.getId());
		assertEquals("laura", result.getUsername());
	}

	@Test
	public void Test_UpdateUser_SuccessFul() throws Exception {
		// Act
		User user = new User(1, "laura", "456", "Laura Morales");

		when(userService.updateUser(1, user)).thenReturn(user);

		// Arrange
		User result = userController.updateUser(1, user);

		// Assert
		assertEquals("456", result.getPassword());
		assertEquals("Laura Morales", result.getName());
	}
	
	@Test
	public void Test_DeleteUser_SuccessFul() throws Exception {

	    doNothing().when(userService).deleteUser(1);

	    String result = userController.deleteUser(1);

	    verify(userService).deleteUser(1);
	    assertEquals("Usuario eliminado correctamente", result);
	}
}