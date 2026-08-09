package com.inventario.Inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.Inventario.model.LoginResponse;
import com.inventario.Inventario.model.User;
import com.inventario.Inventario.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // REGISTRO
    public User register(User user) throws Exception {
        if (repo.findByUsername(user.getUsername()) != null) throw new Exception("El usuario ya existe");

        return repo.save(user);
    }

    // LOGIN
    public LoginResponse login(String username, String password) throws Exception {
        User user = repo.findByUsername(username);

        if (user == null) throw new Exception("Usuario no encontrado");

        if (!user.getPassword().equals(password)) throw new Exception("Contraseña incorrecta");

        return new LoginResponse(user.getId(), user.getUsername(), user.getName());
    }

    // LISTAR TODOS
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // BUSCAR POR ID
    public User getUserById(Integer id) throws Exception {
        Optional<User> user = repo.findById(id);

        if (!user.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }

        return user.get();
    }

    // ACTUALIZAR
    public User updateUser(Integer id, User newUser) throws Exception {
        User user = getUserById(id);
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setName(newUser.getName());

        return repo.save(user);
    }

    // ELIMINAR
    public void deleteUser(Integer id) throws Exception {
        User user = getUserById(id);
        repo.delete(user);
    }

}