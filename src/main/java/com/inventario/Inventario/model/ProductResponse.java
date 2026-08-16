package com.inventario.Inventario.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private String mensaje;
    private Product producto;
}