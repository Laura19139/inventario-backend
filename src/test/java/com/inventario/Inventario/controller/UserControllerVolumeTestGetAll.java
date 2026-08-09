package com.inventario.Inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.Inventario.model.User;

@SpringBootTest
public class UserControllerVolumeTestGetAll {

    @Autowired
    private UserController userController;

    @Test
    public void testListarUsuarios() throws Exception {

        int numeroRegistros = 1000;

        // Registrar usuarios para la prueba
        for (int i = 1; i <= numeroRegistros; i++) {

            User user = new User();
            user.setUsername("usuario_list_" + i);
            user.setPassword("123456");
            user.setName("Usuario List " + i);

            userController.register(user);
        }

        long inicio = System.currentTimeMillis();

        // Consultar todos los usuarios varias veces
        for (int i = 0; i < numeroRegistros; i++) {
            userController.getAllUsers();
        }

        long fin = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("PRUEBA DE VOLUMEN - LISTAR USUARIOS");
        System.out.println("Consultas realizadas: " + numeroRegistros);
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
        System.out.println("-----------------------------------------");
    }
}