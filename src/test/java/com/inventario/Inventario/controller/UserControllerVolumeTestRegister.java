package com.inventario.Inventario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.Inventario.model.User;

@SpringBootTest
public class UserControllerVolumeTestRegister {

    @Autowired
    private UserController userController;

    @Test
    public void testRegistrarUsuarios() throws Exception {

        int numeroRegistros = 1000;

        long inicio = System.currentTimeMillis();

        for (int i = 1; i <= numeroRegistros; i++) {

            User user = new User();
            user.setUsername("usuario_reg_" + i);
            user.setPassword("123456");
            user.setName("Usuario Registro " + i);

            userController.register(user);
        }

        long fin = System.currentTimeMillis();

        System.out.println("-----------------------------------------");
        System.out.println("PRUEBA DE VOLUMEN - REGISTRAR USUARIOS");
        System.out.println("Usuarios registrados: " + numeroRegistros);
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
        System.out.println("-----------------------------------------");
    }
}