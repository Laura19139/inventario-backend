package com.inventario.Inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.Inventario.model.User;

@SpringBootTest
public class UserControllerVolumeTestGetById {

    @Autowired
    private UserController userController;

    @Test
    public void testBuscarUsuariosPorId() throws Exception {

        int numeroRegistros = 1000;

        // Registrar usuarios para la prueba
        for (int i = 1; i <= numeroRegistros; i++) {

            User user = new User();
            user.setUsername("usuario_buscar_" + i);
            user.setPassword("123456");
            user.setName("Usuario Buscar " + i);

            userController.register(user);
        }

        long inicio = System.currentTimeMillis();

        // Consultar cada usuario por su ID
        for (User user : userController.getAllUsers()) {
            userController.getUserById(user.getId());
        }

        long fin = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("PRUEBA DE VOLUMEN - BUSCAR USUARIO POR ID");
        System.out.println("Consultas realizadas: " + numeroRegistros);
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
        System.out.println("-----------------------------------------");
    }
}