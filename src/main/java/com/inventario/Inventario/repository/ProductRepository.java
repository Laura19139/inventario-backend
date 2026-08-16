package com.inventario.Inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.Inventario.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}