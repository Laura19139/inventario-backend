package com.inventario.Inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventario.Inventario.model.Product;
import com.inventario.Inventario.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    // CREAR PRODUCTO
    public Product createProduct(Product product) throws Exception {

        // Validar nombre
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }

        if (product.getName().trim().length() < 2) {
            throw new Exception("El nombre del producto debe tener mínimo 2 caracteres");
        }

        // Validar precio
        if (product.getPrice() == null) {
            throw new Exception("El precio del producto es obligatorio");
        }

        if (product.getPrice() <= 0) {
            throw new Exception("El precio debe ser mayor que 0");
        }

        // Validar cantidad
        if (product.getQuantity() == null) {
            throw new Exception("La cantidad del producto es obligatoria");
        }

        if (product.getQuantity() < 0) {
            throw new Exception("La cantidad no puede ser negativa");
        }

        Product savedProduct = repo.save(product);

        return savedProduct;
    }


    // LISTAR TODOS
    public List<Product> getAllProducts() {

        List<Product> products = repo.findAll();

        return products;
    }


    // BUSCAR POR ID
    public Product getProductById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new Exception("El ID del producto no es válido");
        }

        Optional<Product> product = repo.findById(id);

        if (!product.isPresent()) {
            throw new Exception("Producto no encontrado con el ID: " + id);
        }

        return product.get();
    }


    // ACTUALIZAR
    public Product updateProduct(Integer id, Product newProduct) throws Exception {

        // Verificar que exista
        Product product = getProductById(id);

        // Validar nombre
        if (newProduct.getName() == null || newProduct.getName().trim().isEmpty()) {
            throw new Exception("El nombre del producto es obligatorio");
        }

        if (newProduct.getName().trim().length() < 2) {
            throw new Exception("El nombre del producto debe tener mínimo 2 caracteres");
        }

        // Validar precio
        if (newProduct.getPrice() == null) {
            throw new Exception("El precio del producto es obligatorio");
        }

        if (newProduct.getPrice() <= 0) {
            throw new Exception("El precio debe ser mayor que 0");
        }

        // Validar cantidad
        if (newProduct.getQuantity() == null) {
            throw new Exception("La cantidad del producto es obligatoria");
        }

        if (newProduct.getQuantity() < 0) {
            throw new Exception("La cantidad no puede ser negativa");
        }

        // Actualizar información
        product.setName(newProduct.getName().trim());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());

        Product updatedProduct = repo.save(product);

        return updatedProduct;
    }


    // ELIMINAR
    public void deleteProduct(Integer id) throws Exception {

        Product product = getProductById(id);

        repo.delete(product);
    }
}