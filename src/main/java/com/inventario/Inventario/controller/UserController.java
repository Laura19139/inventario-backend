package com.inventario.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventario.Inventario.model.LoginRequest;
import com.inventario.Inventario.model.LoginResponse;
import com.inventario.Inventario.model.User;
import com.inventario.Inventario.service.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    // REGISTRO
    @PostMapping("/register")
    public User register(@RequestBody User user) throws Exception {
        return service.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest user) throws Exception {
        return service.login(user.getUsername(), user.getPassword());
    }

    // LISTAR TODOS
    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) throws Exception {
        return service.getUserById(id);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) throws Exception {
        return service.updateUser(id, user);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) throws Exception {
        service.deleteUser(id);
        return "Usuario eliminado correctamente";
    }

}