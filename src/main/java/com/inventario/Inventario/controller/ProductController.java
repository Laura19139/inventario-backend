package com.inventario.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.inventario.Inventario.model.Product;
import com.inventario.Inventario.model.ProductResponse;
import com.inventario.Inventario.service.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    // CREAR PRODUCTO
    @PostMapping
    public ProductResponse createProduct(
            @RequestBody Product product) throws Exception {

        Product savedProduct = service.createProduct(product);

        return new ProductResponse(
                "Producto creado correctamente",
                savedProduct
        );
    }

    // LISTAR TODOS
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Integer id) throws Exception {

        return service.getProductById(id);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Integer id,
            @RequestBody Product product) throws Exception {

        Product updatedProduct =
                service.updateProduct(id, product);

        return new ProductResponse(
                "Producto actualizado correctamente",
                updatedProduct
        );
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ProductResponse deleteProduct(
            @PathVariable Integer id) throws Exception {

        Product product = service.getProductById(id);

        service.deleteProduct(id);

        return new ProductResponse(
                "Producto eliminado correctamente",
                product
        );
    }
}