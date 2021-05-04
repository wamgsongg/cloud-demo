package com.ws.dao;


import com.ws.database.Product;
import com.ws.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
