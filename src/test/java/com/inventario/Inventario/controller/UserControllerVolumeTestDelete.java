package com.inventario.Inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.Inventario.model.User;

@SpringBootTest
public class UserControllerVolumeTestDelete {

    @Autowired
    private UserController userController;

    @Test
    public void testEliminarUsuarios() throws Exception {

        int numeroRegistros = 1000;

        // Registrar usuarios para la prueba
        for (int i = 1; i <= numeroRegistros; i++) {

            User user = new User();
            user.setUsername("usuario_delete_" + i);
            user.setPassword("123456");
            user.setName("Usuario Delete " + i);

            userController.register(user);
        }

        long inicio = System.currentTimeMillis();

        // Obtener los usuarios registrados y eliminarlos
        for (User user : userController.getAllUsers()) {
            userController.deleteUser(user.getId());
        }

        long fin = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("PRUEBA DE VOLUMEN - ELIMINAR USUARIOS");
        System.out.println("Usuarios eliminados: " + numeroRegistros);
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
        System.out.println("-----------------------------------------");
    }
}