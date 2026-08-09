package com.inventario.Inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.Inventario.model.User;

@SpringBootTest
public class UserControllerVolumeTestUpdate {

    @Autowired
    private UserController userController;

    @Test
    public void testActualizarUsuarios() throws Exception {

        int numeroRegistros = 1000;

        // Registrar usuarios para la prueba
        for (int i = 1; i <= numeroRegistros; i++) {

            User user = new User();
            user.setUsername("usuario_update_" + i);
            user.setPassword("123456");
            user.setName("Usuario Update " + i);

            userController.register(user);
        }

        long inicio = System.currentTimeMillis();

        // Actualizar todos los usuarios registrados
        for (User user : userController.getAllUsers()) {

            User usuarioActualizado = new User();
            usuarioActualizado.setUsername(user.getUsername() + "_mod");
            usuarioActualizado.setPassword("654321");
            usuarioActualizado.setName(user.getName() + " Modificado");

            userController.updateUser(user.getId(), usuarioActualizado);
        }

        long fin = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("PRUEBA DE VOLUMEN - ACTUALIZAR USUARIOS");
        System.out.println("Usuarios actualizados: " + numeroRegistros);
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
        System.out.println("-----------------------------------------");
    }
}