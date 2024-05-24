package com.repaso.producto.repository;
import com.repaso.producto.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductRepository extends JpaRepository<Product,Integer> {

    Boolean existsByCodigo(String codigo);

}
