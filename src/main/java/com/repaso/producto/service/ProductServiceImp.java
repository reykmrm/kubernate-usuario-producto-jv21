package com.repaso.producto.service;
import com.repaso.producto.models.entity.Product;
import com.repaso.producto.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void crearP(Product product) {
        if(validarFecha(product)){
            productRepository.save(product);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> porId(int id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> obtenerTodos() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void eliminarP(int id) {
        productRepository.deleteById(id);
    }

    //la validacion de la fecha
    //no se puede poner una fecha antes de la fecha actual, en el modificar y crear
    public Boolean validarFecha(Product product){
        LocalDate hoy = LocalDate.now();
        LocalDate fechaProduct = product.getFechaVencimiento();
        int dias = (int) ChronoUnit.DAYS.between(hoy,fechaProduct);
        return dias >= 0;//true
    }
}
