package com.repaso.producto.service;
import com.repaso.producto.models.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    void crearP(Product product);

    Optional<Product> porId(int id);

    List<Product> obtenerTodos();

    void eliminarP(int id);
}
